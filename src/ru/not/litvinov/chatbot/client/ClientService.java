package ru.not.litvinov.chatbot.client;

public interface ClientService {
    Client getModel();
    void saveModel(Client client);
}
