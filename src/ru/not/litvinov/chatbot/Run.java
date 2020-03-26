package ru.not.litvinov.chatbot;


public class Run {
    public static void main(String[] args) {
        Bot server = new Bot();
        Client client = new Client(server);
        client.initialize();
    }
}
