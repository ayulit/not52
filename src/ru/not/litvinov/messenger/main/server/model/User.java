package ru.not.litvinov.messenger.main.server.model;

import ru.not.litvinov.messenger.main.server.helper.ServerHelper;

public class User {
    private Integer userId;
    private String username;
    private String password;

    public User(String username, String password) {
        this.userId = ServerHelper.assignUserId(username);
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
