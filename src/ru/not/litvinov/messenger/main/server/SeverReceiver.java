package ru.not.litvinov.messenger.main.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SeverReceiver extends Thread {
    private static final int RECEIVING_PORT = 3456;
    private static final int QUEUE_SIZE = 1000;
    BlockingQueue<String> inQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(RECEIVING_PORT);
            Socket clientSocket = serverSocket.accept();
            DataInputStream in = new DataInputStream(clientSocket.getInputStream())) {

            System.out.println("Client is online.");
            (new ServerTransmitter(inQueue)).start();

            // receive messages from client
            while (true) {
                if(in.available() != 0) {
                    String messageFromClient = in.readUTF();
                    System.out.println(messageFromClient);

                    inQueue.add(messageFromClient);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
