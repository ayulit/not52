package ru.not.litvinov.chatbot;

public interface ClientView {
    void show(History history);
    void show(ClientPresence presence);
    void showName(String username);
    void showAnswer(String name, String answer);
    void changePresence();
}
