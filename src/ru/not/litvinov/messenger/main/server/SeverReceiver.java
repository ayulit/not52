package ru.not.litvinov.messenger.main.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SeverReceiver extends Thread {
    private static final int RECEIVING_PORT = 3456;

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(RECEIVING_PORT);
            Socket clientSocket = serverSocket.accept()) {

            (new ServerTransmitter()).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
