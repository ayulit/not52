package ru.not.litvinov.chatbot.client;

import ru.not.litvinov.chatbot.Bot;
import ru.not.litvinov.chatbot.client.history.HistoryService;
import ru.not.litvinov.chatbot.client.history.WriterHistoryService;
import ru.not.litvinov.chatbot.client.view.ClientView;

import java.io.IOException;
import java.util.Scanner;

public class ClientController {

    private static final String EXIT = "exit";
    private static final String PRESENCE = "presence";

    private Bot server;
    private Client clientModel;
    private ClientView clientView;

    private HistoryService historyService = new WriterHistoryService();

    public ClientController(Bot server, ClientView clientView, Client model) throws IOException {
        this.server = server;
        this.clientView = clientView;
        this.clientModel = model;
    }

    public void initialize() throws IOException {
        connect(server);
        startConversation();
        disconnect(server);
    }

    private void disconnect(Bot server) {
        server.stop();
    }

    private void startConversation() {
        clientView.show(historyService.loadHistory()); // can be deserialized
        clientView.show(clientModel.getPresence());

        /* Main Loop */
        try (Scanner sc = new Scanner(System.in)) {
            String inputMessage;
            do {
                clientView.showName(clientModel.getUsername());

                inputMessage = sc.next();
                if (EXIT.equals(inputMessage)) {
                    break;
                } else if (PRESENCE.equals(inputMessage)) {
                    changePresence(sc);
                    clientView.show(clientModel.getPresence());
                    continue;
                }
                historyService.logMessage(clientModel.getUsername(), inputMessage);

                String answerMessage = send(inputMessage);

                clientView.showAnswer(server.BOT_NAME, answerMessage);
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
        clientView.changePresence();
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
