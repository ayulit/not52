package ru.not.litvinov.messenger.main.client.controller;

import ru.not.litvinov.messenger.main.client.service.ClientHistoryService;
import ru.not.litvinov.messenger.main.shared.model.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ClientTransmitter {
    private static final String SERVER_MESSENGER_HOST = "localhost";
    private static final int TRANSMIT_PORT = 8080;

    private BlockingQueue<Message> queue;

    private ClientHistoryService historyService;

    public ClientTransmitter(BlockingQueue<Message> queue, ClientHistoryService historyService) {
        this.queue = queue;
        this.historyService = historyService;
    }

    public void transmit() {
        try(Socket clientSocket = new Socket(SERVER_MESSENGER_HOST, TRANSMIT_PORT);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

            List<Message> messages = new ArrayList<>();
            while(!queue.isEmpty()) {
                Message message = queue.take();
                messages.add(message);

//                historyService.save(message);
            }
                out.writeObject(messages);
//                out.flush();
        } catch (ConnectException e) {
            System.out.println("Server unavailable.");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
