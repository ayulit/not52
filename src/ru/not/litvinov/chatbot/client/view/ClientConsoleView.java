package ru.not.litvinov.chatbot.client.view;

import ru.not.litvinov.chatbot.client.model.ClientPresence;
import ru.not.litvinov.chatbot.history.History;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class ClientConsoleView implements ClientView {

    private DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                    .withLocale(Locale.US)
                    .withZone(ZoneId.systemDefault());

    @Override
    public void show(ClientPresence presence) {
        System.out.println("Client's presence: " + presence);
        System.out.println("Please enter a word.");
        System.out.println(" - type 'exit' for exit");
        System.out.println(" - type 'presence' to change presence");
    }

    @Override
    public void show(History history) {
        for (String line: history.getLines()) {
            System.out.println(line);
        }
    }

    @Override
    public void showName(String username) {
        System.out.print(username + ": ");
    }

    @Override
    public void showAnswer(String name, String answer) {
        showName(name);
        System.out.println(answer + " [" + dateTimeFormatter.format(Instant.now()) + "]");
    }

    @Override
    public void changePresence() {
        System.out.println("Please, select presence:");
        System.out.println("    0 - Online");
        System.out.println("    1 - Away");
        System.out.println("    2 - Busy");
    }
}
