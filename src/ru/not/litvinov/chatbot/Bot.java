package ru.not.litvinov.chatbot;

public class Bot {
    public static final String BOT_NAME = "BOT";

    public void initialize() {
        System.out.println("Chat-bot initialized...");
    }

    public void stop() {
        System.out.println(BOT_NAME + ": Goodbye...");
    }

    public String replyOn(String message) {
        return new StringBuilder(message).reverse().toString();
    }
}
