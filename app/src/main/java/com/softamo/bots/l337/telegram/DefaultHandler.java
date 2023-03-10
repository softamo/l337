package com.softamo.bots.l337.telegram;

import io.micronaut.chatbots.core.SpaceParser;
import io.micronaut.chatbots.telegram.api.Chat;
import io.micronaut.chatbots.telegram.api.Update;
import io.micronaut.chatbots.telegram.api.send.SendMessage;
import io.micronaut.chatbots.telegram.core.SendMessageUtils;
import io.micronaut.chatbots.telegram.core.TelegramBotConfiguration;
import io.micronaut.chatbots.telegram.core.TelegramHandler;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Singleton;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Singleton
class DefaultHandler implements TelegramHandler<SendMessage> {
    private final SpaceParser<Update, Chat> spaceParser;

    DefaultHandler(SpaceParser<Update, Chat> spaceParser) {
        this.spaceParser = spaceParser;
    }

    @Override
    public boolean canHandle(@Nullable TelegramBotConfiguration bot,
                             @NotNull @Valid Update input) {
        return true;
    }

    @Override
    @NonNull
    public Optional<SendMessage> handle(@Nullable TelegramBotConfiguration bot,
                                        @NotNull @Valid Update input) {
        return SendMessageUtils.compose(spaceParser, input, "Hello World");
    }
}
