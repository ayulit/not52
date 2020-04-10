package ru.not.litvinov.lec09.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(3456);
            Socket socket = serverSocket.accept(); // blocking, waiting for connection
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server done.");

    }
}
