package ru.not.litvinov.messenger.main.client.controller;

import ru.not.litvinov.messenger.main.client.service.ClientHistoryService;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.BlockingQueue;

public class ClientReceiver extends Thread {

    private BlockingQueue<String> queue;
    private int receivePort;

    private ClientHistoryService historyService;

    public ClientReceiver(BlockingQueue<String> queue, int receivePort, ClientHistoryService historyService) {
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
                    DataInputStream in = new DataInputStream(clientSocket.getInputStream())) {

                    // FIXME squeeze stream
                    while (true) {
                        String receivedMessage = in.readUTF();
                        queue.add(receivedMessage);
//                        historyService.save(receivedMessage);
                    }
                } catch (EOFException e) {
//                    System.out.println("No messages for now");
                    // FIXME
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
