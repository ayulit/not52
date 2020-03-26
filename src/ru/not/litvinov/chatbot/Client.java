package ru.not.litvinov.chatbot;

public class Client {
    private static final String USER = "YOU";

    private ClientStatus status;
    private String username = USER;

    public ClientStatus getStatus() {
        return status;
    }
    public void setStatus(ClientStatus status) {
        this.status = status;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
