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

    void show(ClientPresence presence) {
        System.out.println("Client's presence: " + presence);
        System.out.println("Please enter a word.");
        System.out.println(" - type 'exit' for exit");
        System.out.println(" - type 'presence' to change presence");
    }

    public void printName(String username) {
        System.out.print(username + ": ");
    }

    public void printAnswer(String name, String answer) {
        printName(name);
        System.out.println(answer + " [" + dateTimeFormatter.format(Instant.now()) + "]");
    }

    public void changePresence() {
        System.out.println("Please, select presence:");
        System.out.println("    0 - Online");
        System.out.println("    1 - Away");
        System.out.println("    2 - Busy");
    }
}
