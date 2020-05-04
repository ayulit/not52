package ru.not.litvinov.messenger.main.server.service;

import ru.not.litvinov.messenger.main.shared.model.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerHistoryService {

    private static Map<Integer, List<Message>> history = new HashMap<>();

    public List<Message> getHistoryById(Integer clientId) {

        if(!history.containsKey(clientId)) {
            history.put(clientId, new ArrayList<>());
        }

        return history.get(clientId);
    }

    public boolean saveForSender(Message message) {
        Integer senderId = message.getSenderId();
        return save(senderId, message);
    }

    public boolean saveForConsumer(Message message) {
        Integer consumerId = message.getConsumerId();
        return save(consumerId, message);
    }

    private boolean save(Integer clientId, Message message) {
        return getHistoryById(clientId).add(message);
    }
}
