package ru.not.litvinov.messenger.main.client.helper;

public enum Clients {
    ANNA(8081),
    CAROL(8082);

    private final int clientId;

    Clients(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }
}
