package ru.not.litvinov.lec09.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket  = new Socket("localhost", 3456); // connecting, tcp-handshake
            InputStream inputStream = socket.getInputStream();

            System.out.println("Read: " + inputStream.read()); // ready to read, read

            socket.close(); // don't forget to close socket
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Client done.");
    }
}
