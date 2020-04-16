package ru.not.litvinov.messenger.main.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

public class ServerTransmitter extends Thread {
    private static final String CLIENT_MESSENGER_HOST = "localhost";
    private static final int TRANSMISSION_PORT = 3457;

    private BlockingQueue<String> queue;

    public ServerTransmitter(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try(Socket clientSocket = new Socket(CLIENT_MESSENGER_HOST, TRANSMISSION_PORT);
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            out.writeUTF("history");
            out.flush();

            out.writeUTF("messages");
            out.flush();

            while (true) {
                if(!queue.isEmpty()) {
                    String messageFromReceiver = queue.take();
                    String replyMessage = new StringBuilder(messageFromReceiver).reverse().toString();

                    out.writeUTF(replyMessage);
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
