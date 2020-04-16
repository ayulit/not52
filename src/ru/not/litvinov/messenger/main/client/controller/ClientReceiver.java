package ru.not.litvinov.messenger.main.client.controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ClientReceiver extends Thread {
    private static final int RECEIVING_PORT = 3457;

    BlockingQueue<String> queue;

    public ClientReceiver(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(RECEIVING_PORT);
            Socket clientSocket = serverSocket.accept();
            DataInputStream in = new DataInputStream(clientSocket.getInputStream())) {

            // receive history
            System.out.println(in.readUTF());

            // receive messages
            System.out.println(in.readUTF());

            while (true) {
                if(in.available() !=0) {
                    queue.add(in.readUTF());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
