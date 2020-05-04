package ru.not.litvinov.messenger.main.shared.model;

import java.io.Serializable;

public class Message implements Serializable {
    private final String message;
    private Integer senderId;
    private Integer consumerId;

    public Message(Integer from, Integer to, String message) {
        this.senderId = from;
        this.consumerId = to;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    @Override
    public String toString() {
        return message;
    }
}
