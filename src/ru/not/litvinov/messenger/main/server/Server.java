package ru.not.litvinov.messenger.main.server;

import ru.not.litvinov.messenger.main.server.service.MessageService;
import ru.not.litvinov.messenger.main.server.service.MessageServiceImpl;
import ru.not.litvinov.messenger.main.shared.model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {

    private static final int QUEUE_SIZE = 1000;

    private static MessageService messageService = new MessageServiceImpl();

    public static void main(String[] args) {
        System.out.println("Server is operational...");

        /* Server's main loop (server never sleeps) */
        while(true) {

            /* ServerReceiver */
            try(ServerSocket serverSocket = new ServerSocket(8080);
                Socket clientSocket = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {



                // read the list of messages from the socket
                List<Message> messagesFromClient = (List<Message>) in.readObject();

                for (Message msg: messagesFromClient) {

                    System.out.println("Received: " + msg);


                    if(msg.getMessage().startsWith("AUTH:")) {

                        // check my messages
                        // TODO send history?

                        //FIXME loopback
                        Integer id = msg.getSenderId();
                        messageService.addMessage(new Message(id, id, "HISTORY:"));

                        System.out.println("Hello, Client #" + msg.getSenderId());

                    } else {

                        // save message to DB
                        messageService.addMessage(msg);
    //                friendsQueue.add(messageFromClient);

                        // TODO historystamp
    //                    historyService.save(userid, messageFromClient);

    //                queueService.getQueue(messageFromClient.getReceiverId())

                    }
                    ServerTransmitter transmitter = new ServerTransmitter(messageService, msg.getConsumerId()); // 📡
                    transmitter.start();
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Client disconnected.");
            }



        }

    }
}
