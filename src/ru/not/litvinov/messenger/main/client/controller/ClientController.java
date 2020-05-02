package ru.not.litvinov.messenger.main.client.controller;

import java.util.concurrent.BlockingQueue;

public class ClientController extends Thread {

    private BlockingQueue<String> inQueue;
    private BlockingQueue<String> outQueue;

    private int transmitPort;
    private int receivePort;

    public ClientController(int transmitPort, int receivePort, BlockingQueue<String> inQueue, BlockingQueue<String> outQueue) {
        this.transmitPort = transmitPort;
        this.receivePort = receivePort;
        this.inQueue = inQueue;
        this.outQueue = outQueue;
    }

    @Override
    public void run() {
        ClientReceiver receiver = new ClientReceiver(inQueue, receivePort); // 👂
        receiver.start();

        ClientTransmitter transmitter = new ClientTransmitter(outQueue, transmitPort); // 📡

        // TODO somehow fetch history

        while (!Thread.interrupted()) {
            // loop transmitting if present: as smth appears in the queue, open tcp connection
            if(!outQueue.isEmpty()) {
                transmitter.transmit();
            }
        }

        // gently stop receiver
        receiver.interrupt();

        System.out.println("ClientController / ClientTransmitter closed.");
    }
}
