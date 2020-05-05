package ru.not.litvinov.messenger.main.client.helper;

import ru.not.litvinov.messenger.main.shared.model.Client;

import static ru.not.litvinov.messenger.main.client.helper.Friends.ANNA;
import static ru.not.litvinov.messenger.main.client.helper.Friends.CAROL;

/* FIXME some hardcode for now */
public class ClientHelper {
    public static Client getFriend(Client client) {
        Friends friend = null;

        if (ANNA.getClient().equals(client)) {
            friend = CAROL;
        } else if (CAROL.getClient().equals(client)) {
            friend = ANNA;
        }

        return friend.getClient();
    }
}
