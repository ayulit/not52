package ru.not.litvinov.chatbot.client.service;

import ru.not.litvinov.chatbot.client.model.Client;

public interface ClientService {
    Client getModel();
    void saveModel(Client client);
}
