package com.softamo.telegram.l337;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.micronaut.chatbots.telegram.lambda.Handler;
import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DefaultHandlerTest {
    private static Map<String, Object> PROPS = Map.of("micronaut.chatbots.telegram.bots.mn-bot.token", "xxx",
            "micronaut.chatbots.telegram.bots.mn-bot.at-username", "@MnBot");

    @Test
    void handleHasEmptyConstructor() {
        assertDoesNotThrow(() -> new Handler());
    }

    @Test
    void handlerResponds401IfHttpTTPHeaderXTelegramBotApiSecretTokenIsNotPresent() throws IOException {
        Handler handler = new Handler(ApplicationContext.builder().properties(PROPS));

        File f = new File("src/test/resources/text.json");
        assertTrue(f.exists());
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setBody(TestUtils.text(f));
        APIGatewayProxyResponseEvent response = handler.handleRequest(request, null);
        assertEquals(401, response.getStatusCode());
        handler.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {"X-Telegram-Bot-Api-Secret-Token", "x-telegram-bot-api-secret-token"})
    void handlerResponds200AndMessageIsNotPresent(String header) throws IOException {
        String value = "xxx";
        Map<String, Object> properties = new HashMap<>(PROPS);
        Handler handler = new Handler(ApplicationContext.builder().properties(properties));
        File f = new File("src/test/resources/text.json");
        assertTrue(f.exists());


        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setBody(TestUtils.text(f));
        request.setHeaders(Collections.singletonMap(header, value));
        APIGatewayProxyResponseEvent response = handler.handleRequest(request, null);

        assertEquals(200, response.getStatusCode());
        assertEquals("{\"text\":\"Hello World\",\"method\":\"sendMessage\",\"chat_id\":718265379}", response.getBody());
        handler.close();
    }
}
