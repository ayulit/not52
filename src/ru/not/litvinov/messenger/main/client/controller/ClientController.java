package ru.not.litvinov.messenger.main.client.controller;

public class ClientController {

    public void initialize() {
        (new ClientReceiver()).start();
        (new ClientTransmitter()).start();
    }
}
