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

public class Bot {
    private static final String BOT = "BOT: ";
    private static final String USER = "YOU: ";
    private static final String EXIT = "exit";

    private File history = new File ("history.log");

    private DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                    .withLocale(Locale.UK)
                    .withZone(ZoneId.systemDefault());

    void initialize() {
        loadHistory();
        sayHello();
        keepConversation();
        sayGoodbye();
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

    void sayHello() {
        System.out.println("Chat-bot initialized...");
        System.out.println(BOT + "Hello! Please enter a word. For quit enter 'exit'.");
    }

    void sayGoodbye() {
        System.out.println(BOT + "Goodbye...");
    }

    void keepConversation() {
        try (Scanner sc = new Scanner(System.in);
             FileWriter fw = new FileWriter(history, history.exists());
             BufferedWriter writer = new BufferedWriter(fw)) {

            String input;
            do {
                print(USER, writer); input = sc.next(); writer.write(input + "\n");
                String answer = replyOn(input);
                println(BOT + answer , writer);
            } while (!EXIT.equals(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String replyOn(String input) {
        return new StringBuilder(input).reverse().toString();
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
