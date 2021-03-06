package ru.not.litvinov.chatbot.client.view;

import ru.not.litvinov.chatbot.client.model.ClientPresence;
import ru.not.litvinov.chatbot.history.History;

public interface ClientView {
    void show(History history);
    void show(ClientPresence presence);
    void showName(String username);
    void showAnswer(String name, String answer);
    void changePresence();
}
