package ru.not.litvinov.messenger.main.client.view;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class ClientConsoleView extends Thread {
    private static final String EXIT = "exit";

    private BlockingQueue<String> inQueue;
    private BlockingQueue<String> outQueue;

    public ClientConsoleView(BlockingQueue<String> inQueue, BlockingQueue<String> outQueue) {
        this.inQueue = inQueue;
        this.outQueue = outQueue;
    }

    class ClientConsoleWriter extends Thread {
        @Override
        public void run() {
            while (true) {
                if(!inQueue.isEmpty()) {
                    String receivedMessage = null;

                    try {
                        receivedMessage = inQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(receivedMessage);
                }
            }
        }
    }

    public void draw() {
        (new ClientConsoleWriter()).start();
        /* Main Loop */
        try (Scanner sc = new Scanner(System.in)) {
            String inputMessage;
            do {
                inputMessage = sc.next();

                outQueue.put(inputMessage);

//                if (EXIT.equals(inputMessage)) {
//                    break;
//                }
            } while (true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
