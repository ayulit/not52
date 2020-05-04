package ru.not.litvinov.messenger.main.client.controller;

import ru.not.litvinov.messenger.main.client.service.ClientHistoryService;
import ru.not.litvinov.messenger.main.shared.model.Message;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ClientReceiver extends Thread {

    private BlockingQueue<Message> queue;
    private int receivePort;

    private ClientHistoryService historyService;

    public ClientReceiver(BlockingQueue<Message> queue, int receivePort, ClientHistoryService historyService) {
        this.queue = queue;
        this.receivePort = receivePort;
        this.historyService = historyService;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try (ServerSocket serverSocket = new ServerSocket(receivePort)) {
                serverSocket.setSoTimeout(3000);
                try(Socket clientSocket = serverSocket.accept();
                    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

                    // read the list of messages from the socket
                    List<Message> receivedMessages = (List<Message>) in.readObject();

                    queue.addAll(receivedMessages);

//                        historyService.save(receivedMessage);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (SocketTimeoutException e) {
                // TODO
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ClientReceiver closed");
    }
}
