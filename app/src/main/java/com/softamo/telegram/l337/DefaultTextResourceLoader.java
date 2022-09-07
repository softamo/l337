/*
 * Copyright 2017-2022 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.softamo.telegram.l337;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import io.micronaut.core.util.StringUtils;
import jakarta.inject.Singleton;

import javax.validation.constraints.NotBlank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Singleton
public class DefaultTextResourceLoader implements TextResourceLoader {

    private static final String SLASH = "/";
    private static final String EMPTY = "";
    private final ConcurrentHashMap<String, String> commandText = new ConcurrentHashMap<>();
    private final ResourceResolver resourceResolver;

    public DefaultTextResourceLoader(ResourceResolver resourceResolver) {
        this.resourceResolver = resourceResolver;
    }

    @Override
    @NonNull
    public Optional<String> parseText(@NonNull @NotBlank String command) {
        return candidates(command)
                .map(fileName -> commandText.computeIfAbsent(fileName, s -> textFromPath(s).orElse(EMPTY)))
                .filter(StringUtils::isNotEmpty)
                .findFirst();
    }

    @NonNull
    protected Stream<String> candidates(@NonNull String command) {
        String cmd = command.startsWith(SLASH) ? command.substring(1) : command;
        return Stream.of(FileExtension.values())
                .flatMap(ext -> Stream.of(ext.getExtensions()).map(fileExtension -> cmd + "." + fileExtension));
    }

    @NonNull
    private Optional<String> textFromPath(@NonNull String path) {
        Optional<ClassPathResourceLoader> loaderOptional = resourceResolver.getLoader(ClassPathResourceLoader.class);
        if (!loaderOptional.isPresent()) {
            return Optional.empty();
        }
        ClassPathResourceLoader loader = loaderOptional.get();
        Optional<URL> resource = loader.getResource(path);
        if (!resource.isPresent()) {
            return Optional.empty();
        }
        URL url = resource.get();
        URLConnection urlConnection = null;
        try {
            urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            String text = readFromInputStream(inputStream);
            inputStream.close();
            return Optional.of(text);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
