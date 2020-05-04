package ru.not.litvinov.messenger.main.client.helper;

import static ru.not.litvinov.messenger.main.client.helper.Clients.ANNA;
import static ru.not.litvinov.messenger.main.client.helper.Clients.CAROL;

public class ClientHelper {
    public static Integer getFriendId(Clients clientId) {
        Clients friend;

        switch (clientId) {
            case ANNA:
                friend = CAROL;
                break;
            case CAROL:
            default:
                friend = ANNA;
                break;
        }

    return friend.getClientId();
    }

    public static int getPort(Clients client) {
        return client.getClientId();
    }
}
