package com.softamo.telegram.l337;

import software.amazon.awscdk.App;

public class Main {
    public static void main(final String[] args) {
        App app = new App();
        new AppStack(app, "L337Stack");
        app.synth();
    }
}