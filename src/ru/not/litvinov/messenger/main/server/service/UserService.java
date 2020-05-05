package ru.not.litvinov.messenger.main.server.service;

import ru.not.litvinov.messenger.main.server.model.User;
import ru.not.litvinov.messenger.main.shared.model.Client;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static Map<String, User> users = new HashMap<>();

    public Client addUser(Client sender, String credentials) {

        String username = credentials.split("/")[0];
        String password = credentials.split("/")[1];

        // to DB
        User user = new User(username, password);
        users.put(username, user);

        // back to client as a DTO
        Client client = new Client(sender.getReceivePort(), user.getUserId());

        return client;
    }

    public Client authUser(Client sender, String credentials) {

        String username = credentials.split("/")[0];
        String password = credentials.split("/")[1];

        if(users.containsKey(username)) {
            User user = users.get(username);
            if(password.equals(user.getPassword())) {
              return new Client(sender.getReceivePort(), user.getUserId());
            }
        }

        // TODO exceptions
        return new Client(sender.getReceivePort(), null);
    }

}
