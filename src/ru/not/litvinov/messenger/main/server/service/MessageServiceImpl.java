package ru.not.litvinov.messenger.main.server.service;

import ru.not.litvinov.messenger.main.shared.model.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageServiceImpl implements MessageService {
    private static final int QUEUE_SIZE = 1000;

    private static Map<Integer, BlockingQueue<Message>> queuePool = new HashMap<>();

    @Override
    public boolean addMessage(Message message) {

        Integer consumerId = message.getConsumerId();

        if(!queuePool.containsKey(consumerId)) {
            queuePool.put(consumerId, new ArrayBlockingQueue<>(QUEUE_SIZE));
        }

        return queuePool.get(consumerId).add(message);
    }

    @Override
    public BlockingQueue<Message> getMessagesByConsumerId(Integer consumerId) {
        if(!queuePool.containsKey(consumerId)) {
            queuePool.put(consumerId, new ArrayBlockingQueue<>(QUEUE_SIZE));
        }
        return queuePool.get(consumerId);
    }
}
