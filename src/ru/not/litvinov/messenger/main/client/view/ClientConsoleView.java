package ru.not.litvinov.messenger.main.client.view;

import ru.not.litvinov.messenger.main.client.controller.ClientReceiver;
import ru.not.litvinov.messenger.main.client.controller.ClientTransmitter;
import ru.not.litvinov.messenger.main.client.helper.ClientHelper;
import ru.not.litvinov.messenger.main.client.service.ClientHistoryService;
import ru.not.litvinov.messenger.main.shared.model.Client;
import ru.not.litvinov.messenger.main.shared.model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.List;

public class ClientConsoleView implements ClientView {
    private static final String EXIT = "exit";
    private static final int MIN_PORT_NUMBER = 8081;
    private static final int MAX_PORT_NUMBER = 8090;

    private Client me;

    private ClientTransmitter clientTransmitter = new ClientTransmitter();
    private ClientReceiver clientReceiver;

    private ClientHistoryService historyService = new ClientHistoryService();
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public ClientConsoleView() {
        int receivePort = assignPort();
        this.me = new Client(receivePort);
        this.clientReceiver = new ClientReceiver(receivePort);
    }

    private int assignPort() {
        for (int port = MIN_PORT_NUMBER; port <= MAX_PORT_NUMBER; port++) {
            if(available(port)) {
                return port;
            }
        }
        
        // FIXME exception?
        return 8080;
    }

    private boolean available(int port) {
        if (port < MIN_PORT_NUMBER || port > MAX_PORT_NUMBER) {
            throw new IllegalArgumentException("Invalid start port: " + port);
        }
        try (ServerSocket ss = new ServerSocket(port)) {
            ss.setReuseAddress(true);
            return true;
        } catch (IOException ignored) {

        }
        return false;
    }

    class ClientConsoleWriter extends Thread {
        @Override
        public void run() {

            // print history already synchronized at this point
            List<Message> historyMessages = historyService.getHistoryList();
            historyMessages.forEach(System.out::println);

            while (!Thread.interrupted()) {
                List<Message> receivedMessages = clientReceiver.receive(); // blocking

                if(!receivedMessages.isEmpty()) {
                    receivedMessages.forEach(System.out::println);
                }
            }
        }
    }

    public String acquireCredentials() {
        String username = null;
        String password = null;
        try {
            do {
                System.out.print("Username: ");
                username = input.readLine();
                System.out.print("Password: ");
                password = input.readLine();
            } while (!validation(username, password));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return username + "/" + password;
    }

    public void register() {
        System.out.println("Register.");
        // sending to server
        String credentials = acquireCredentials();
        System.out.println("Sending credentials: " + credentials);

        Message request = new Message(me, me, "REG:" + credentials);

        clientTransmitter.transmit(request);
        // blocking wait for response
        Message response = clientReceiver.receiveServiceMessage();
        System.out.println(response);

        this.me = response.getConsumer();

        // TODO: handle errors
        // username exists
    }

    public void login() {
        System.out.println("Login.");
        // sending to server
        String credentials = acquireCredentials();
        System.out.println("Sending credentials: " + credentials);

        Message request = new Message(me, me, "AUTH:" + credentials);

        clientTransmitter.transmit(request);
        // blocking wait for response
        Message response = clientReceiver.receiveServiceMessage();
        System.out.println(response);

        this.me = response.getConsumer();

        // TODO: handle errors
        // wrong password
        // user not found
    }

    private void syncHistory() {
        Message request = new Message(me, me, "HIST:");
        System.out.println("Fetching history...");

        clientTransmitter.transmit(request);

        // blocking wait for response
        List<Message> historyFromServer = clientReceiver.receive();
        historyService.sync(historyFromServer);
    }

    private boolean validation(String credential) {
        boolean valid = true;
        if(credential == null) {
            System.out.print("Credential can't be null. ");
            return false;
        }
        if(credential.isEmpty()) {
            System.out.print("Credential can't be empty. ");
            return false;
        }
        if (Character.isDigit(credential.charAt(0))) {
            System.out.print("Credential can't begin with a digit. ");
            valid = false;
        }
        if (credential.contains(" ")) {
            System.out.print("No spaces. ");
            valid = false;
        }
        if (credential.contains(":")) {
            System.out.print("No colon. ");
            valid = false;
        }
        if (credential.contains("/")) {
            System.out.print("No slash. ");
            valid = false;
        }
        return valid;
    }

    private boolean validation(String username, String password) {
        boolean valid = true;

        if(!validation(username)) {
            System.out.println("Invalid username.");
            valid = false;
        }
        if(!validation(password)) {
            System.out.println("Invalid password.");
            valid = false;
        }
        if(!valid) {
            System.out.println("Try again...");
        }
        return valid;
    }

    private void authentication() {
        try {
            do {
                System.out.print("'1' - LOGIN, '2' - REG, '0' - EXIT: [1] ");

                int option = 1; // default

                String str = input.readLine();

                if(!str.isEmpty()) {
                    try {
                        option = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input. Try again...");
                        continue;
                    }
                }

                if(option == 0) {
                    // EXIT
                    break;
                } else if(option == 1) {
                    login();

                    // FIXME
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    syncHistory();
                    break;
                } else if(option == 2) {
                    register();
                    break;
                } else {
                    System.out.println("Wrong option. Try again...");
                }


            } while (true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {
        // REG or LOGIN
        authentication();
        System.out.println("My ID is #" + me.getUserId() + "\n");

        ClientConsoleWriter clientConsoleWriter = new ClientConsoleWriter();
        clientConsoleWriter.start();


        /* Messenging main Loop */
        try {
            String inputMessage;
            do {
                inputMessage = input.readLine();
                if (EXIT.equals(inputMessage)) {
                    break;
                }
                Message message = new Message(me, ClientHelper.getFriend(me), inputMessage);

                clientTransmitter.transmit(message);

            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            clientConsoleWriter.interrupt();
        }
    }
}
