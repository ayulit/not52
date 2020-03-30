package ru.not.litvinov.chatbot;

import java.io.IOException;

public interface HistoryService {
    History loadHistory(); // load from file / deserialization
    void logMessage(String username, String message) throws IOException;
    void flushHistory() throws IOException;
}
