package ru.not.litvinov.messenger.main.client.controller;

import ru.not.litvinov.messenger.main.client.service.ClientHistoryService;

import java.util.concurrent.BlockingQueue;

public class ClientController extends Thread {

    private BlockingQueue<String> inQueue;
    private BlockingQueue<String> outQueue;

    private ClientHistoryService historyService;

    private int transmitPort;
    private int receivePort;

    public ClientController(int transmitPort, int receivePort, BlockingQueue<String> inQueue, BlockingQueue<String> outQueue, ClientHistoryService historyService) {
        this.transmitPort = transmitPort;
        this.receivePort = receivePort;
        this.inQueue = inQueue;
        this.outQueue = outQueue;
        this.historyService = historyService;
    }

    @Override
    public void run() {
        ClientReceiver receiver = new ClientReceiver(inQueue, receivePort, historyService); // 👂
        receiver.start();

        ClientTransmitter transmitter = new ClientTransmitter(outQueue, transmitPort, historyService); // 📡

        // TODO somehow synchronize history while first connection

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
