package ru.not.litvinov.messenger.main.server;

import ru.not.litvinov.messenger.main.server.service.MessageService;
import ru.not.litvinov.messenger.main.server.service.MessageServiceImpl;
import ru.not.litvinov.messenger.main.server.service.ServerHistoryService;
import ru.not.litvinov.messenger.main.server.service.UserService;
import ru.not.litvinov.messenger.main.shared.model.Client;
import ru.not.litvinov.messenger.main.shared.model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private static MessageService messageService = new MessageServiceImpl();
    private static ServerHistoryService historyService = new ServerHistoryService();
    private static UserService userService = new UserService();

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
                Client sender = msg.getSender();
                Client consumer = msg.getConsumer();
                String text = msg.getText();

                System.out.println("Hi, my port is #" + sender.getReceivePort() + ": " + msg);

                ServerTransmitter transmitter = new ServerTransmitter(messageService, consumer, historyService);

                if(text.startsWith("REG:")) {

                    String credentials = text.split(":")[1];
                    Client client = userService.addUser(sender, credentials);

                    System.out.println("User #" + client.getUserId() + " registered.");

                    Message response = new Message(null, client, "Registered.");

                    transmitter.transmitServiceMessage(response);

                    // FIXME THIS: for some reason ClientReceiver is not it time with processing it
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else if(text.startsWith("AUTH:")) {
                    String credentials = text.split(":")[1];
                    Client client = userService.authUser(sender, credentials);

                    System.out.println("Hello, User #" + client.getUserId());

                    Message response = new Message(null, client, "Authenticated.");
                    transmitter.transmitServiceMessage(response);

                    // FIXME THIS: for some reason ClientReceiver is not it time with processing it
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if(text.startsWith("HIST:")) {
                    List<Message> historyMessages = historyService.getHistoryById(sender.getUserId());
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
