package ru.not.litvinov.messenger.main.server;

import ru.not.litvinov.messenger.main.server.helper.ServerHelper;
import ru.not.litvinov.messenger.main.server.service.MessageService;
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

    private Integer consumerId;

    public ServerTransmitter(MessageService msgService, Integer consumerId) {
        messageService = msgService;
        this.consumerId = consumerId;
    }

    @Override
    public void run() {
        BlockingQueue<Message> queue = messageService.getMessagesByConsumerId(consumerId);

                try (Socket clientSocket = new Socket(CLIENT_MESSENGER_HOST, ServerHelper.getPort(consumerId));
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    List<Message> messagesFromFriend = new ArrayList<>();

                    while (!queue.isEmpty()) {
                        Message messageFromFriend = queue.take();

                        System.out.println("Sending ... " + messageFromFriend);

                        messagesFromFriend.add(messageFromFriend);

                        // TODO historystamp
//                        historyService.save(userid, messageFromFriend);

                    }

                        out.writeObject(messagesFromFriend);
//                        out.flush();

                } catch (ConnectException e) {
//                    System.out.println("Client unavailable.");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
    }
}
