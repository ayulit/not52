package ru.not.litvinov.chatbot;


import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        Bot server = new Bot();
        ClientController clientController = new ClientController(server, new ClientConsoleView(), new Client());
        clientController.initialize();
    }
}
