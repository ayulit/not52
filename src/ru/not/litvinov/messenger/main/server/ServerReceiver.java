package ru.not.litvinov.messenger.main.server;

import java.io.DataInputStream;
import java.io.EOFException;
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
        ServerTransmitter serverTransmitter = new ServerTransmitter(transmitPort, replyQueue); // 📡
        serverTransmitter.start();
        System.out.println("Server transmitter started.");
        System.out.println("Server receiver started.");

        /* Server's main loop (server never sleeps) */
        while(true) {
            try(ServerSocket serverSocket = new ServerSocket(receivePort);
                Socket clientSocket = serverSocket.accept();
                DataInputStream in = new DataInputStream(clientSocket.getInputStream())) {

                // FIXME squeeze stream: receive messages from client or throw EOFException
                while (true) {
                    String messageFromClient = in.readUTF();
                    System.out.println("Received: " + messageFromClient);
                    friendsQueue.add(messageFromClient);
                }
            } catch (EOFException e) {
//                System.out.println("No messages for now.");
                // FIXME
            } catch (IOException  e) {
                e.printStackTrace();
            } finally {
                System.out.println("Client disconnected.");
            }
        }
    }
}
