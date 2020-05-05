package ru.not.litvinov.messenger.main.server;

import ru.not.litvinov.messenger.main.server.service.MessageService;
import ru.not.litvinov.messenger.main.server.service.ServerHistoryService;
import ru.not.litvinov.messenger.main.shared.model.Client;
import ru.not.litvinov.messenger.main.shared.model.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ServerTransmitter extends Thread {
    private static final String CLIENT_MESSENGER_HOST = "localhost";

    private static MessageService messageService;

    private static ServerHistoryService historyService;

    private Client consumer;

    public ServerTransmitter(MessageService msgService, Client consumer, ServerHistoryService histService) {
        messageService = msgService;
        historyService = histService;
        this.consumer = consumer;
    }

    public boolean transmitServiceMessage(Message message) {

        int port = message.getConsumer().getReceivePort();

        try (Socket clientSocket = new Socket(CLIENT_MESSENGER_HOST, port);
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

            out.writeObject(message);

            return true;
        } catch (ConnectException e) {
            // TODO
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean transmit(List<Message> messages) {
        try (Socket clientSocket = new Socket(CLIENT_MESSENGER_HOST, consumer.getReceivePort());
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

                out.writeObject(messages);

                return true;
        } catch (ConnectException e) {
            // TODO
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean transmit(BlockingQueue<Message> queue) {
        try (Socket clientSocket = new Socket(CLIENT_MESSENGER_HOST, consumer.getReceivePort());
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

            List<Message> messages = new ArrayList<>();
            while (!queue.isEmpty()) {
                    Message messageFromFriend = queue.take();

                    System.out.println("Sending to #" + messageFromFriend.getConsumer().getUserId() + ": " + messageFromFriend);

                    messages.add(messageFromFriend);

                    historyService.saveForConsumer(messageFromFriend);
            }

            out.writeObject(messages);

            return true;

        } catch (ConnectException e) {
            // TODO
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void run() {
        BlockingQueue<Message> queue = messageService.getMessagesByConsumerId(consumer.getUserId());
        transmit(queue);
    }
}
