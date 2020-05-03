package ru.not.litvinov.messenger.main.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server {

    private static final int QUEUE_SIZE = 1000;

    public static void main(String[] args) {

        // TODO should be implemented as a ThreadPool or some collection
        BlockingQueue<String> queueForAnna = new ArrayBlockingQueue<>(QUEUE_SIZE);
        BlockingQueue<String> queueForCarol = new ArrayBlockingQueue<>(QUEUE_SIZE);

        ServerReceiver annaReceiver = new ServerReceiver(3456, 3457, queueForAnna, queueForCarol);  // 👂




        ServerReceiver carolReceiver = new ServerReceiver(3556, 3557, queueForCarol, queueForAnna); // 👂
        annaReceiver.start();
        carolReceiver.start();

        System.out.println("Server is operational...");
    }
}
