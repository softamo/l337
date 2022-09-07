package com.softamo.telegram.l337;

import io.micronaut.chatbots.core.SpaceParser;
import io.micronaut.chatbots.telegram.api.Chat;
import io.micronaut.chatbots.telegram.api.Update;
import io.micronaut.chatbots.telegram.api.send.SendMessage;
import io.micronaut.core.annotation.NonNull;

import java.util.Optional;

public class SendMessageUtils {

    @NonNull
    public static Optional<SendMessage> compose(@NonNull SpaceParser<Update, Chat> spaceParser,
                                                 @NonNull Update update,
                                                 @NonNull String text) {
        return spaceParser.parse(update)
                .map(space -> compose(space, text));

    }

    @NonNull
    public static SendMessage compose(@NonNull Chat space,
                                       @NonNull String text) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(space.getId());
        return message;
    }
}
