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
        ClientReceiver receiver = new ClientReceiver(inQueue, receivePort);
        ClientTransmitter transmitter = new ClientTransmitter(outQueue, transmitPort);

        receiver.start();

        transmitter.initialize();
    }
}
