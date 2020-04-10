package ru.not.litvinov.lec09.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3456);

            System.out.println("Waiting...");
            Socket socket = serverSocket.accept(); // blocking, waiting for connection
            System.out.println("Connected");

            socket.getOutputStream().write(5); // BIN 101

            // dont't forget to close sockets
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server done.");

    }
}
