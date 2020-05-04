package ru.not.litvinov.messenger.main.client.controller;

import ru.not.litvinov.messenger.main.client.helper.ClientHelper;
import ru.not.litvinov.messenger.main.client.helper.Clients;
import ru.not.litvinov.messenger.main.client.service.ClientHistoryService;
import ru.not.litvinov.messenger.main.shared.model.Message;

import java.util.concurrent.BlockingQueue;

public class ClientController extends Thread {

    private BlockingQueue<Message> inQueue;
    private BlockingQueue<Message> outQueue;

    private ClientHistoryService historyService;

    private Clients clientId;

    public ClientController(Clients clientId, BlockingQueue<Message> inQueue, BlockingQueue<Message> outQueue, ClientHistoryService historyService) {
        this.clientId = clientId;
        this.inQueue = inQueue;
        this.outQueue = outQueue;
        this.historyService = historyService;
    }

    @Override
    public void run() {
        ClientReceiver receiver = new ClientReceiver(inQueue, ClientHelper.getPort(clientId), historyService); // 👂
        receiver.start();

        ClientTransmitter transmitter = new ClientTransmitter(outQueue, historyService); // 📡

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
