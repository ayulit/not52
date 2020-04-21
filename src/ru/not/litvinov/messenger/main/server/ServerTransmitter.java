package ru.not.litvinov.messenger.main.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
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
        try(Socket clientSocket = new Socket(CLIENT_MESSENGER_HOST, transmitPort);
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            out.writeUTF("history");
            out.flush();

            out.writeUTF("messages");
            out.flush();

            while (true) {
                if(!queue.isEmpty()) {
                    String receivedMessage = queue.take();
                    out.writeUTF(receivedMessage);
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
