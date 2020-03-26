package ru.not.litvinov.chatbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Scanner;

public class Client {
    private static final String USER = "YOU: ";
    private static final String EXIT = "exit";

    private File history = new File ("history.log");

    private DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                    .withLocale(Locale.UK)
                    .withZone(ZoneId.systemDefault());

    private Status status;
    private Bot server;

    public Client(Bot server) {
        this.server = server;
    }

    void initialize() {
        establishStatus();
        connect(server);

        loadHistory();
        startConversation();

        disconnect(server);
    }

    private void disconnect(Bot server) {
        server.stop();
    }

    void loadHistory() {
        if(history.exists()) {
            try (FileReader fr = new FileReader(history);
                 BufferedReader in = new BufferedReader(fr)) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startConversation() {

        System.out.println("Client's status: " + status);
        System.out.println("Please enter a word. For quit type 'exit'.");

        try (Scanner sc = new Scanner(System.in);
             FileWriter fw = new FileWriter(history, history.exists());
             BufferedWriter writer = new BufferedWriter(fw)) {

            String inputMessage;
            do {
                print(USER, writer);
                inputMessage = sc.next();
                writer.write(inputMessage + "\n");

                String answerMessage = send(inputMessage);

                println(server.BOT_NAME + answerMessage , writer);
            } while (!EXIT.equals(inputMessage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String send(String message) {
        return server.replyOn(message);
    }

    private void connect(Bot server) {
        server.initialize();
    }

    private void establishStatus() {
        // set default
        status = Status.ONLINE;
    }

    private void print(String s, BufferedWriter writer) throws IOException {
        s = "[" + formatter.format(Instant.now()) + "] " + s;
        System.out.print(s);
        writer.write(s);
    }

    private void println(String s, BufferedWriter writer) throws IOException {
        s = "[" + formatter.format(Instant.now()) + "] " + s + "\n";
        System.out.print(s);
        writer.write(s);
    }
}
