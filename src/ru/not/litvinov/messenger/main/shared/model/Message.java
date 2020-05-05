package ru.not.litvinov.messenger.main.shared.model;

import java.io.Serializable;

public class Message implements Serializable {
    private final String text;
    private Client sender;
    private Client consumer;

    public Message(Client from, Client to, String text) {
        this.sender = from;
        this.consumer = to;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Client getSender() {
        return sender;
    }

    public Client getConsumer() {
        return consumer;
    }

    @Override
    public String toString() {
        return text;
    }
}
