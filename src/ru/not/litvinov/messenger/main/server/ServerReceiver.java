package ru.not.litvinov.messenger.main.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ServerReceiver extends Thread {

    private int receivePort;
    private int transmitPort;

    private BlockingQueue<String> replyQueue;
    private BlockingQueue<String> friendsQueue;

    public ServerReceiver(int receivePort, int transmitPort, BlockingQueue<String> replyQueue, BlockingQueue<String> friendsQueue) {
        this.receivePort = receivePort;
        this.transmitPort = transmitPort;

        this.replyQueue = replyQueue;
        this.friendsQueue = friendsQueue;
    }

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(receivePort);
            Socket clientSocket = serverSocket.accept();
            DataInputStream in = new DataInputStream(clientSocket.getInputStream())) {

            System.out.println("Client is online.");
            (new ServerTransmitter(transmitPort, replyQueue)).start();

            // receive messages from client
            while (true) {
                if(in.available() != 0) {
                    String messageFromClient = in.readUTF();
                    System.out.println("Received: " + messageFromClient);

                    friendsQueue.add(messageFromClient);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
