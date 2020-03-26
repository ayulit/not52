package ru.not.litvinov.chatbot;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class ClientView {

    private DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                    .withLocale(Locale.US)
                    .withZone(ZoneId.systemDefault());

    void show(ClientStatus status) {
        System.out.println("Client's status: " + status);
        System.out.println("Please enter a word. For quit type 'exit'.");
    }

    public void printName(String username) {
        System.out.print(username + ": ");
    }

    public void printAnswer(String name, String answer) {
        printName(name);
        System.out.println(answer + " [" + dateTimeFormatter.format(Instant.now()) + "]");
    }
}
