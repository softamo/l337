package com.softamo.telegram.l337;

import io.micronaut.chatbots.core.SpaceParser;
import io.micronaut.chatbots.telegram.api.Chat;
import io.micronaut.chatbots.telegram.api.Update;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;

@Singleton
public class AboutHandler extends CommandHandler {
    public static final String COMMAND_ABOUT = "/about";
    public AboutHandler(TelegramSlashCommandParser slashCommandParser,
                        TextResourceLoader textResourceLoader,
                        SpaceParser<Update, Chat> spaceParser) {
        super(slashCommandParser, textResourceLoader, spaceParser);
    }

    @Override
    @NonNull
    public String getCommand() {
        return COMMAND_ABOUT;
    }
}
