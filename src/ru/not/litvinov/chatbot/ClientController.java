package ru.not.litvinov.chatbot;

import java.io.IOException;
import java.util.Scanner;

public class ClientController {

    private static final String EXIT = "exit";
    private static final String STATUS = "status";

    private Bot server;
    private Client model;
    private ClientView view;

    private Logger logger = new Logger();

    public ClientController(Bot server, ClientView view, Client model) throws IOException {
        this.server = server;
        this.view = view;
        this.model = model;
    }

    void initialize() throws IOException {
        connect(server);

        logger.loadHistory();
        startConversation();

        disconnect(server);
        logger.flush();
    }

    private void disconnect(Bot server) {
        server.stop();
    }

    private void startConversation() {
        view.show(model.getStatus());

        /** Main Loop */
        try (Scanner sc = new Scanner(System.in)) {
            String inputMessage;
            do {
                view.printName(model.getUsername());

                inputMessage = sc.next();
                if (EXIT.equals(inputMessage)) {
                    break;
                } else if (STATUS.equals(inputMessage)) {
                    changeStatus(sc);
                    view.show(model.getStatus());
                    continue;
                }
                logger.logMessage(model.getUsername(), inputMessage);

                String answerMessage = send(inputMessage);

                view.printAnswer(server.BOT_NAME, answerMessage);
                logger.logMessage(server.BOT_NAME, answerMessage);
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            model.serialize();
        }
    }

    private void changeStatus(Scanner sc) {
        view.changeStatus();
        ClientStatus clientStatus = ClientStatus.values()[sc.nextInt()];
        model.setStatus(clientStatus);
    }

    private String send(String message) {
        return server.replyOn(message);
    }

    private void connect(Bot server) {
        server.initialize();
    }
}