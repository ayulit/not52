package ru.not.litvinov.messenger.main.client;

import ru.not.litvinov.messenger.main.client.controller.ClientController;

public class ClientMessenger {
    private int transmitPort;
    private int receivePort;

    // FIXME port implement autoassignment mechanism
    public ClientMessenger(int transmitPort, int receivePort) {
        this.transmitPort = transmitPort;
        this.receivePort = receivePort;
    }

    public void init() {
        ClientController clientController = new ClientController(transmitPort, receivePort);
        clientController.initialize();
    }
}
