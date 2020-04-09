package ru.not.litvinov.chatbot.history;

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

public class WriterHistoryService implements HistoryService {
    private File historyFile = new File ("history.log");
    private BufferedWriter writer;

    private DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                    .withLocale(Locale.UK)
                    .withZone(ZoneId.systemDefault());

    public WriterHistoryService() throws IOException {
        writer = new BufferedWriter(new FileWriter(historyFile, historyFile.exists()));
    }

    @Override
    public History loadHistory() {
        History history = new History();
        if(historyFile.exists()) {
            try (FileReader fr = new FileReader(historyFile);
                 BufferedReader in = new BufferedReader(fr)) {
                String line;
                while ((line = in.readLine()) != null) {
//                    System.out.println(line);
                    history.addLine(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return history;
    }

    @Override
    public void logMessage(String username, String message) throws IOException {
        writer.write("[" + dateTimeFormatter.format(Instant.now()) + "] " + username + ": " + message + "\n");
    }

    @Override
    public void flushHistory() throws IOException {
        try {
            writer.flush();
        } finally {
            writer.close();
        }
    }
}
