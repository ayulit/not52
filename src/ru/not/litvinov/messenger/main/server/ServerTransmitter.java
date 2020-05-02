package ru.not.litvinov.messenger.main.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ServerTransmitter extends Thread {
    private static final String CLIENT_MESSENGER_HOST = "localhost";

    private BlockingQueue<String> queue;

    private int transmitPort;

    public ServerTransmitter(int transmitPort, BlockingQueue<String> queue) {
        this.transmitPort = transmitPort;
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            if (!queue.isEmpty()) {
                try (Socket clientSocket = new Socket(CLIENT_MESSENGER_HOST, transmitPort);
                     DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

                    while (!queue.isEmpty()) {
                        String receivedMessage = queue.take();

                        System.out.println("Sending ... " + receivedMessage);

                        out.writeUTF(receivedMessage);
                        out.flush();
                    }
                } catch (ConnectException e) {
//                    System.out.println("Client unavailable.");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
