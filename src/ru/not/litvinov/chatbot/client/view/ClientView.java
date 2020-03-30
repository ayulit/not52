package ru.not.litvinov.chatbot.client.view;

import ru.not.litvinov.chatbot.client.ClientPresence;
import ru.not.litvinov.chatbot.client.history.History;

public interface ClientView {
    void show(History history);
    void show(ClientPresence presence);
    void showName(String username);
    void showAnswer(String name, String answer);
    void changePresence();
}
