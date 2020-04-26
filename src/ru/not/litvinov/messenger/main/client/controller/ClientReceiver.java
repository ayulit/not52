package ru.not.litvinov.messenger.main.client.controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ClientReceiver extends Thread {

    private BlockingQueue<String> queue;
    private int receivePort;

    public ClientReceiver(BlockingQueue<String> queue, int receivePort) {
        this.queue = queue;
        this.receivePort = receivePort;
    }

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(receivePort);
            Socket clientSocket = serverSocket.accept();
            DataInputStream in = new DataInputStream(clientSocket.getInputStream())) {

            // receive history
            System.out.println(in.readUTF());

            // receive messages
            System.out.println(in.readUTF());

            while (true) {
                if(in.available() !=0) {
                    // from receiver to view
                    queue.add(in.readUTF());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
