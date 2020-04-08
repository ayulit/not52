package ru.not.litvinov.chatbot.client;

import java.io.Serializable;

public class Client implements Serializable {
    private static final String USER = "YOU";

    private ClientPresence presence = ClientPresence.ONLINE;
    private String username = USER;

    public ClientPresence getPresence() {
        return presence;
    }
    public void setPresence(ClientPresence presence) {
        this.presence = presence;
    }
    public String getUsername() {
        return username;
    }
}
