package ru.not.litvinov.messenger.main.client.view;

import ru.not.litvinov.messenger.main.client.helper.ClientHelper;
import ru.not.litvinov.messenger.main.client.helper.Clients;
import ru.not.litvinov.messenger.main.shared.model.Message;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class ClientConsoleView extends Thread {
    private static final String EXIT = "exit";

    private BlockingQueue<Message> inQueue;
    private BlockingQueue<Message> outQueue;

    private Clients client;

    public ClientConsoleView(BlockingQueue<Message> inQueue, BlockingQueue<Message> outQueue, Clients client) {
        this.inQueue = inQueue;
        this.outQueue = outQueue;
        this.client = client;
    }

    class ClientConsoleWriter extends Thread {
        @Override
        public void run() {

            // TODO print history (already synchronized at this point)
//            System.out.println(historyService.getHistory());

            while (!Thread.interrupted()) {
                if(!inQueue.isEmpty()) {
                    Message receivedMessage = null;
                    try {
                        receivedMessage = inQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(receivedMessage);
                }
            }
            System.out.println("ClientConsoleWriter closed.");
        }
    }

    public void draw() {
        ClientConsoleWriter clientConsoleWriter = new ClientConsoleWriter();
        clientConsoleWriter.start();

        /* Main Loop */
        try (Scanner sc = new Scanner(System.in)) {

            // FIXME auth
            // loopsend
            outQueue.put(new Message(client.getClientId(), client.getClientId(), "AUTH:username_pwd"));

            String inputMessage;
            do {
                inputMessage = sc.next();
                if (EXIT.equals(inputMessage)) {
                    break;
                }
                outQueue.put(new Message(client.getClientId(), ClientHelper.getFriendId(client), inputMessage));
            } while (true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            clientConsoleWriter.interrupt();
        }
    }
}
