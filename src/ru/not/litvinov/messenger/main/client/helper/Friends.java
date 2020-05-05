package ru.not.litvinov.messenger.main.client.helper;

import ru.not.litvinov.messenger.main.shared.model.Client;

public enum Friends {
    ANNA(new Client(8081, 51)),
    CAROL(new Client(8082, 52));

    private final Client client;

    Friends(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
