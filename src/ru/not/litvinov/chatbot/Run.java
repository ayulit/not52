package ru.not.litvinov.chatbot;


import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        Bot server = new Bot();
        ClientController clientController = new ClientController(server, new ClientView(), new Client());
        clientController.initialize();
    }
}
