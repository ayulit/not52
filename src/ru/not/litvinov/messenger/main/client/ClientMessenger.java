package ru.not.litvinov.messenger.main.client;

import ru.not.litvinov.messenger.main.client.controller.ClientController;

public class ClientMessenger {
    public void init() {
        ClientController clientController = new ClientController();
        clientController.initialize();
    }
}
