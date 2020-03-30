package ru.not.litvinov.chatbot;


import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        ClientController clientController = new ClientController(new Bot(), new ClientConsoleView(), new Client());
        clientController.initialize();
    }
}
