package ru.not.litvinov.messenger.main.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerTransmitter extends Thread {
    private static final String CLIENT_MESSENGER_HOST = "localhost";
    private static final int TRANSMISSION_PORT = 3457;

    @Override
    public void run() {
        try(Socket clientSocket = new Socket(CLIENT_MESSENGER_HOST, TRANSMISSION_PORT);
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {
            
            out.writeUTF("history");
            out.flush();

            out.writeUTF("messages");
            out.flush();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
