package ru.not.litvinov.messenger.main.server;

public class Server {
    public static void main(String[] args) {
        (new SeverReceiver()).start();
    }
}
