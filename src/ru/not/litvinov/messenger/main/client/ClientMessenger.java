package ru.not.litvinov.messenger.main.client;

import ru.not.litvinov.messenger.main.client.controller.ClientController;
import ru.not.litvinov.messenger.main.client.view.ClientConsoleView;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ClientMessenger {
    private static final int QUEUE_SIZE = 1000;

    private int transmitPort;
    private int receivePort;

    private BlockingQueue<String> inQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);
    private BlockingQueue<String> outQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);

    // FIXME port implement autoassignment mechanism
    public ClientMessenger(int transmitPort, int receivePort) {
        this.transmitPort = transmitPort;
        this.receivePort = receivePort;
    }

    public void run() {
        // Controller in another thread
        ClientController clientController = new ClientController(transmitPort, receivePort, inQueue, outQueue);
        clientController.start();

        // View must be in the main thread
        ClientConsoleView view = new ClientConsoleView(inQueue, outQueue);
        view.draw();

        System.out.println("ClientConsoleView closed.");

        clientController.interrupt();
    }
}
