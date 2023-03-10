package com.softamo.bots.l337.telegram;

import software.amazon.awscdk.App;

public class Main {
    public static void main(final String[] args) {
        App app = new App();
        new AppStack(app, "TelegramL337");
        app.synth();
    }
}