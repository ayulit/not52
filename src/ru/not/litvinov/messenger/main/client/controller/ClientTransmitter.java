package ru.not.litvinov.messenger.main.client.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTransmitter extends Thread {
    private static final String SERVER_MESSENGER_HOST = "localhost";
    private static final int TRANSMISSION_PORT = 3456;

    @Override
    public void run() {
        try(Socket clientSocket = new Socket(SERVER_MESSENGER_HOST, TRANSMISSION_PORT)) {

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
