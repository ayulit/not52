package ru.not.litvinov.messenger.test;

import ru.not.litvinov.messenger.main.client.ClientMessenger;

import static ru.not.litvinov.messenger.main.client.helper.Clients.ANNA;

public class Anna {
    public static void main(String[] args) {
        System.out.println("Hello, Anna!");
        (new ClientMessenger(ANNA)).run();
    }
}
