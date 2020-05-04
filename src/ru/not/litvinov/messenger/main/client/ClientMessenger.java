package ru.not.litvinov.messenger.main.client;

import ru.not.litvinov.messenger.main.client.controller.ClientController;
import ru.not.litvinov.messenger.main.client.helper.Clients;
import ru.not.litvinov.messenger.main.client.service.ClientHistoryService;
import ru.not.litvinov.messenger.main.client.view.ClientConsoleView;
import ru.not.litvinov.messenger.main.shared.model.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ClientMessenger {
    private static final int QUEUE_SIZE = 1000;

    private Clients clientId;

    private BlockingQueue<Message> inQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);
    private BlockingQueue<Message> outQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);

    private ClientHistoryService historyService = new ClientHistoryService();

    public ClientMessenger(Clients clientId) {
        this.clientId = clientId;
    }

    public void run() {
        // Controller in another thread
        ClientController clientController = new ClientController(clientId, inQueue, outQueue, historyService);
        clientController.start();

        // View must be in the main thread
        ClientConsoleView view = new ClientConsoleView(inQueue, outQueue, clientId);
        view.draw();

        System.out.println("ClientConsoleView closed.");

        clientController.interrupt();
    }
}
