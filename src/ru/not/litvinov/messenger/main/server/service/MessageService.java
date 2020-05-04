package ru.not.litvinov.messenger.main.server.service;

import ru.not.litvinov.messenger.main.shared.model.Message;

import java.util.concurrent.BlockingQueue;

public interface MessageService {
    boolean addMessage(Message message);
    BlockingQueue<Message> getMessagesByConsumerId(Integer consumerId);
}
