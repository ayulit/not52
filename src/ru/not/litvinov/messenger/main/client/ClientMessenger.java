package ru.not.litvinov.messenger.main.client;

import ru.not.litvinov.messenger.main.client.view.ClientConsoleView;
import ru.not.litvinov.messenger.main.client.view.ClientView;

public class ClientMessenger {
    public void exec() {
        System.out.println("Client started.");

        ClientView view = new ClientConsoleView();
        view.draw(); // blocking

        System.out.println("Bye-bye...");
    }
}
