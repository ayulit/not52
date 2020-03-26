package ru.not.litvinov.chatbot;

public class Bot {
    public static final String BOT_NAME = "BOT: ";

    void initialize() {
        sayHello();
    }

    void stop() {
        sayGoodbye();
    }

    void sayHello() {
        System.out.println("Chat-bot initialized...");
    }

    void sayGoodbye() {
        System.out.println(BOT_NAME + "Goodbye...");
    }

    public String replyOn(String message) {
        return new StringBuilder(message).reverse().toString();
    }
}
