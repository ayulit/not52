package ru.not.litvinov.messenger.test;

import ru.not.litvinov.messenger.main.client.ClientMessenger;

import static ru.not.litvinov.messenger.main.client.helper.Clients.CAROL;

public class Carol {
    public static void main(String[] args) {
        (new ClientMessenger(CAROL)).exec();
    }
}
