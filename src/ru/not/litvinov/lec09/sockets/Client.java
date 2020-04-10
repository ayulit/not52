package ru.not.litvinov.lec09.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 3456); // connecting, tcp-handshake
            InputStream inputStream = socket.getInputStream();
            inputStream.read(); // ready to read, read
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Client done.");
    }
}
