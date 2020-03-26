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

public class Logger {
    private File history = new File ("history.log");
    private BufferedWriter writer;

    private DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                    .withLocale(Locale.UK)
                    .withZone(ZoneId.systemDefault());


    public Logger() throws IOException {
        writer = new BufferedWriter(new FileWriter(history, history.exists()));
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

    public void logMessage(String username, String message) throws IOException {
        writer.write("[" + dateTimeFormatter.format(Instant.now()) + "] " + username + ": " + message + "\n");
    }

    public void flush() throws IOException {
        writer.flush();
        writer.close();
    }
}
