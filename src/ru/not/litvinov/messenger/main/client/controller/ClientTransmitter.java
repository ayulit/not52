package ru.not.litvinov.messenger.main.client.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ClientTransmitter {
    private static final String SERVER_MESSENGER_HOST = "localhost";

    private BlockingQueue<String> queue;
    private int transmitPort;

    public ClientTransmitter(BlockingQueue<String> queue, int transmitPort) {
        this.queue = queue;
        this.transmitPort = transmitPort;
    }

    public void transmit() {
        try(Socket clientSocket = new Socket(SERVER_MESSENGER_HOST, transmitPort);
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            while(!queue.isEmpty()) {
                out.writeUTF(queue.take());
                out.flush();
            }
        } catch (ConnectException e) {
            System.out.println("Server unavailable.");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
