package ru.not.litvinov.messenger.main.server;

import ru.not.litvinov.messenger.main.server.helper.ServerHelper;
import ru.not.litvinov.messenger.main.server.service.MessageService;
import ru.not.litvinov.messenger.main.server.service.ServerHistoryService;
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

    private Integer consumerId;

    public ServerTransmitter(MessageService msgService, Integer consumerId, ServerHistoryService histService) {
        messageService = msgService;
        historyService = histService;
        this.consumerId = consumerId;
    }

    public boolean transmit(List<Message> messages) {
        try (Socket clientSocket = new Socket(CLIENT_MESSENGER_HOST, ServerHelper.getPort(consumerId));
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
        try (Socket clientSocket = new Socket(CLIENT_MESSENGER_HOST, ServerHelper.getPort(consumerId));
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

            List<Message> messages = new ArrayList<>();
            while (!queue.isEmpty()) {
                    Message messageFromFriend = queue.take();

                    System.out.println("Sending to #" + messageFromFriend.getConsumerId() + ": " + messageFromFriend);

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
        BlockingQueue<Message> queue = messageService.getMessagesByConsumerId(consumerId);
        transmit(queue);
    }
}
