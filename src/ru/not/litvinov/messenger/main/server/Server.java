package ru.not.litvinov.messenger.main.server;

import ru.not.litvinov.messenger.main.server.service.MessageService;
import ru.not.litvinov.messenger.main.server.service.MessageServiceImpl;
import ru.not.litvinov.messenger.main.server.service.ServerHistoryService;
import ru.not.litvinov.messenger.main.shared.model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private static MessageService messageService = new MessageServiceImpl();
    private static ServerHistoryService historyService = new ServerHistoryService();

    public static void main(String[] args) {
        System.out.println("Server is operational...\n");

        /* Server's main loop (server never sleeps) */
        while(true) {
            /* ServerReceiver */
            try(ServerSocket serverSocket = new ServerSocket(8080);
                // block
                Socket clientSocket = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

                // read the list of messages from the socket
                Message msg = (Message) in.readObject();

                System.out.println("Received from  #" + msg.getSenderId() + ": " + msg);

                ServerTransmitter transmitter = new ServerTransmitter(messageService, msg.getConsumerId(), historyService);

                if(msg.getMessage().startsWith("AUTH:")) {

                    Integer senderId = msg.getSenderId();

                    List<Message> historyMessages = historyService.getHistoryById(senderId);

                    System.out.println("Hello, Client #" + senderId);

                    /* history first ☝️ */
                    transmitter.transmit(historyMessages);

                    // FIXME THIS: for some reason ClientReceiver is not it time with processing it
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    // save message to DB
                    messageService.addMessage(msg);
                    historyService.saveForSender(msg);
                }

                transmitter.start();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                System.out.println("---\n");
            }
        }
    }
}
