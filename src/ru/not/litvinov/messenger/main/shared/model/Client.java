package ru.not.litvinov.messenger.main.shared.model;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable {
    private Integer receivePort;
    private Integer userId;

    public Client(int receivePort) {
        this.receivePort = receivePort;
    }

    public Client(Integer receivePort, Integer userId) {
        this.receivePort = receivePort;
        this.userId = userId;
    }

    public Integer getReceivePort() {
        return receivePort;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return receivePort.equals(client.receivePort) &&
                Objects.equals(userId, client.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receivePort, userId);
    }
}
