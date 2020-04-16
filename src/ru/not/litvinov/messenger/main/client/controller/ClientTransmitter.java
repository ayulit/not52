package ru.not.litvinov.messenger.main.client.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

public class ClientTransmitter extends Thread {
    private static final String SERVER_MESSENGER_HOST = "localhost";
    private static final int TRANSMISSION_PORT = 3456;

    private BlockingQueue<String> queue;

    public ClientTransmitter(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try(Socket clientSocket = new Socket(SERVER_MESSENGER_HOST, TRANSMISSION_PORT);
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            while (true) {
                if(!queue.isEmpty()) {
                    out.writeUTF(queue.take());
                    out.flush();
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
