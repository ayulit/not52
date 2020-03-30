package ru.not.litvinov.chatbot;


import ru.not.litvinov.chatbot.client.Client;
import ru.not.litvinov.chatbot.client.view.ClientConsoleView;
import ru.not.litvinov.chatbot.client.ClientController;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        ClientController clientController = new ClientController(new Bot(), new ClientConsoleView(), new Client());
        clientController.initialize();
    }
}
