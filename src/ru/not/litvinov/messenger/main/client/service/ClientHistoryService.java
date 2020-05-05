package ru.not.litvinov.messenger.main.client.service;

import ru.not.litvinov.messenger.main.shared.model.Message;

import java.util.Collections;
import java.util.List;

public class ClientHistoryService {

    // FIXME temp solution
    private List<Message> history = Collections.emptyList();

    public List<Message> getHistoryList() {
        return history;
    }

    public boolean sync(List<Message> historyFromServer) {
        this.history = historyFromServer;

        return true;
    }
}
