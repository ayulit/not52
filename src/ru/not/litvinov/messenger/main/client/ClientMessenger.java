package ru.not.litvinov.messenger.main.client;

import ru.not.litvinov.messenger.main.client.helper.Clients;
import ru.not.litvinov.messenger.main.client.view.ClientConsoleView;
import ru.not.litvinov.messenger.main.client.view.ClientView;

public class ClientMessenger {
    private Clients client;

    public ClientMessenger(Clients client) {
        this.client = client;
    }

    public void exec() {
        System.out.println(client + "'s client started.");

        ClientView view = new ClientConsoleView(client);
        view.draw(); // blocking

        System.out.println("Bye-bye...");
    }
}
