package ru.not.litvinov.messenger.main.client.view;

import ru.not.litvinov.messenger.main.client.controller.ClientReceiver;
import ru.not.litvinov.messenger.main.client.controller.ClientTransmitter;
import ru.not.litvinov.messenger.main.client.helper.ClientHelper;
import ru.not.litvinov.messenger.main.client.helper.Clients;
import ru.not.litvinov.messenger.main.shared.model.Message;

import java.util.List;
import java.util.Scanner;

public class ClientConsoleView implements ClientView {
    private static final String EXIT = "exit";

    private Clients client;

    private ClientTransmitter clientTransmitter = new ClientTransmitter();
    private ClientReceiver clientReceiver;

    public ClientConsoleView(Clients client) {
        this.client = client;
        this.clientReceiver = new ClientReceiver(ClientHelper.getPort(client));
    }

    class ClientConsoleWriter extends Thread {
        @Override
        public void run() {

            // TODO print history (already synchronized at this point)
//            System.out.println(historyService.getHistory());

            while (!Thread.interrupted()) {
                List<Message> receivedMessages = clientReceiver.receive();

                if(!receivedMessages.isEmpty()) {
                    receivedMessages.forEach(System.out::println);
                }
            }
        }
    }

    @Override
    public void draw() {
        ClientConsoleWriter clientConsoleWriter = new ClientConsoleWriter();
        clientConsoleWriter.start();

        /* Main Loop */
        try (Scanner sc = new Scanner(System.in)) {

            // FIXME auth
            // loopsend
            Message message = new Message(client.getClientId(), client.getClientId(), "AUTH:username_pwd");
            clientTransmitter.transmit(message);

            String inputMessage;
            do {
                inputMessage = sc.next();
                if (EXIT.equals(inputMessage)) {
                    break;
                }
                message = new Message(client.getClientId(), ClientHelper.getFriendId(client), inputMessage);

                clientTransmitter.transmit(message);

            } while (true);
        } finally {
            clientConsoleWriter.interrupt();
        }
    }
}
