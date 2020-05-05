package ru.not.litvinov.messenger.main.server.helper;

/* FIXME some hardcode for now */
public class ServerHelper {
    public static Integer assignUserId(String username) {
        Integer userId = 0;
        switch (username) {
            case "anna":
                userId = 51;
                break;
            case "carol":
                userId = 52;
                break;
            default:
        }
        return userId;
    }
}
