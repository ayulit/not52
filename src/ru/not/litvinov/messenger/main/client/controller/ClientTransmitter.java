package ru.not.litvinov.messenger.main.client.controller;

import ru.not.litvinov.messenger.main.client.service.ClientHistoryService;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ClientTransmitter {
    private static final String SERVER_MESSENGER_HOST = "localhost";

    private BlockingQueue<String> queue;
    private int transmitPort;

    private ClientHistoryService historyService;

    public ClientTransmitter(BlockingQueue<String> queue, int transmitPort, ClientHistoryService historyService) {
        this.queue = queue;
        this.transmitPort = transmitPort;
        this.historyService = historyService;
    }

    public void transmit() {
        try(Socket clientSocket = new Socket(SERVER_MESSENGER_HOST, transmitPort);
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            while(!queue.isEmpty()) {
                String message = queue.take();
                out.writeUTF(message);
                out.flush();
//                historyService.save(message);
            }
        } catch (ConnectException e) {
            System.out.println("Server unavailable.");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
