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

/**
 * File extension
 * @author Sergio del Amo
 * @since 1.0.0
 */
public enum FileExtension {
    MARKDOWN("md", "markdown"),
    HTML("html"),
    TXT("txt");
    private final String[] extensions;
    FileExtension(String... extensions) {
        this.extensions = extensions;
    }

    @NonNull
    public String[] getExtensions() {
        return extensions;
    }
}
