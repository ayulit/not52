package ru.not.litvinov.messenger.main.client.controller;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ClientController {

    private static final String EXIT = "exit";
    private static final int QUEUE_SIZE = 1000;

    BlockingQueue<String> outQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);
    BlockingQueue<String> inQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);

    private int transmitPort;
    private int receivePort;

    public ClientController(int transmitPort, int receivePort) {
        this.transmitPort = transmitPort;
        this.receivePort = receivePort;
    }

    public void initialize() {
        ClientReceiver receiver = new ClientReceiver(inQueue, receivePort);
        ClientTransmitter transmitter = new ClientTransmitter(outQueue, transmitPort);

        receiver.start();
        transmitter.start();

        /* Main Loop */
        try (Scanner sc = new Scanner(System.in)) {
            String inputMessage;
            do {
                inputMessage = sc.next();

                outQueue.put(inputMessage);

                if (EXIT.equals(inputMessage)) {
                    break;
                }
            } while (true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
