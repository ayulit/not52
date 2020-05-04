package ru.not.litvinov.messenger.main.client.controller;

import ru.not.litvinov.messenger.main.shared.model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.List;

public class ClientReceiver {
    private int receivePort;

    public ClientReceiver(int receivePort) {
        this.receivePort = receivePort;
    }

    public List<Message> receive() {
        try (ServerSocket serverSocket = new ServerSocket(receivePort)) {
            serverSocket.setSoTimeout(3000);
            // block
            try (Socket clientSocket = serverSocket.accept();
                 ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

                // read the list of messages from the socket
                return (List<Message>) in.readObject();
//              historyService.save(receivedMessage);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SocketTimeoutException e) {
            // TODO
        } catch (IOException e) {
            e.printStackTrace();
        }

        // FIXME
        return Collections.emptyList();
    }
}
