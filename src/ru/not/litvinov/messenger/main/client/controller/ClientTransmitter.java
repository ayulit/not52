package ru.not.litvinov.messenger.main.client.controller;

import ru.not.litvinov.messenger.main.shared.model.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class ClientTransmitter {
    private static final String SERVER_MESSENGER_HOST = "localhost";
    private static final int TRANSMIT_PORT = 8080;

    public void transmit(Message message) {
        try(Socket clientSocket = new Socket(SERVER_MESSENGER_HOST, TRANSMIT_PORT);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

            out.writeObject(message);
//          historyService.save(message);

        } catch (ConnectException e) {
            System.out.println("Server unavailable.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
