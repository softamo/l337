package com.softamo.telegram.l337;

import io.micronaut.chatbots.core.SpaceParser;
import io.micronaut.chatbots.telegram.api.Chat;
import io.micronaut.chatbots.telegram.api.Update;
import io.micronaut.chatbots.telegram.api.send.SendMessage;
import io.micronaut.chatbots.telegram.core.TelegramBotConfiguration;
import io.micronaut.chatbots.telegram.core.TelegramHandler;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Singleton;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public abstract class CommandHandler implements TelegramHandler<SendMessage> {

    private final TelegramSlashCommandParser slashCommandParser;
    private final TextResourceLoader textResourceLoader;
    private final SpaceParser<Update, Chat> spaceParser;

    protected CommandHandler(TelegramSlashCommandParser slashCommandParser,
                          TextResourceLoader textResourceLoader,
                          SpaceParser<Update, Chat> spaceParser) {
        this.slashCommandParser = slashCommandParser;
        this.textResourceLoader = textResourceLoader;
        this.spaceParser = spaceParser;
    }

    @NonNull
    public abstract String getCommand();

    @Override
    public boolean canHandle(@Nullable TelegramBotConfiguration bot,
                             @NotNull @Valid Update input) {
        return slashCommandParser.parse(input)
                .filter(command -> command.startsWith(getCommand()))
                .isPresent();
    }

    @Override
    @NonNull
    public Optional<SendMessage> handle(@Nullable TelegramBotConfiguration bot,
                                        @NotNull @Valid Update input) {
        return slashCommandParser.parse(input)
                .flatMap(textResourceLoader::parseText)
                .flatMap(text -> SendMessageUtils.compose(spaceParser, input, text));
    }

    @Override
    public int getOrder() {
        return -10;
    }
}
