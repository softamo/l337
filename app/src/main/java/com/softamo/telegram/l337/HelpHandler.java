package com.softamo.telegram.l337;

import io.micronaut.chatbots.core.SpaceParser;
import io.micronaut.chatbots.telegram.api.Chat;
import io.micronaut.chatbots.telegram.api.Update;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;

@Singleton
public class HelpHandler extends CommandHandler {
    public static final String COMMAND_HELP = "/help";
    public HelpHandler(TelegramSlashCommandParser slashCommandParser,
                       TextResourceLoader textResourceLoader,
                       SpaceParser<Update, Chat> spaceParser) {
        super(slashCommandParser, textResourceLoader, spaceParser);
    }

    @Override
    @NonNull
    public String getCommand() {
        return COMMAND_HELP;
    }
}
