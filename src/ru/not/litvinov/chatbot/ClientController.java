package ru.not.litvinov.chatbot;

import java.io.IOException;
import java.util.Scanner;

public class ClientController {

    private static final String EXIT = "exit";
    private static final String PRESENCE = "presence";

    private Bot server;
    private Client clientModel;
    private ClientConsoleView view;

    private HistoryService historyService = new WriterHistoryService();

    public ClientController(Bot server, ClientConsoleView view, Client model) throws IOException {
        this.server = server;
        this.view = view;
        this.clientModel = model;
    }

    void initialize() throws IOException {
        connect(server);
        startConversation();
        disconnect(server);
    }

    private void disconnect(Bot server) {
        server.stop();
    }

    private void startConversation() {
        view.show(historyService.loadHistory()); // can be deserialized
        view.show(clientModel.getPresence());

        /* Main Loop */
        try (Scanner sc = new Scanner(System.in)) {
            String inputMessage;
            do {
                view.printName(clientModel.getUsername());

                inputMessage = sc.next();
                if (EXIT.equals(inputMessage)) {
                    break;
                } else if (PRESENCE.equals(inputMessage)) {
                    changePresence(sc);
                    view.show(clientModel.getPresence());
                    continue;
                }
                historyService.logMessage(clientModel.getUsername(), inputMessage);

                String answerMessage = send(inputMessage);

                view.printAnswer(server.BOT_NAME, answerMessage);
                historyService.logMessage(server.BOT_NAME, answerMessage);
            } while (true);

            historyService.flushHistory(); // can be serialized
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clientModel.serialize();
        }
    }

    private void changePresence(Scanner sc) {
        view.changePresence();
        ClientPresence clientPresence = ClientPresence.values()[sc.nextInt()];
        clientModel.setPresence(clientPresence);
    }

    private String send(String message) {
        return server.replyOn(message);
    }

    private void connect(Bot server) {
        server.initialize();
    }
}
