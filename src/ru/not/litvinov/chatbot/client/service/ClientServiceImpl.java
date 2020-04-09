package ru.not.litvinov.chatbot.client.service;

import ru.not.litvinov.chatbot.client.model.Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientServiceImpl implements ClientService {

    private static final String PRESENCE_FILENAME = "presence.ser";

    @Override
    public Client getModel() {
        File presenceFile = new File(PRESENCE_FILENAME);
        if(presenceFile.exists()) {
            try (FileInputStream fos = new FileInputStream(presenceFile);
                 ObjectInputStream in = new ObjectInputStream(fos)) {
                return (Client) in.readObject();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        return new Client();
    }

    @Override
    public void saveModel(Client client) {
        try (FileOutputStream fos = new FileOutputStream(PRESENCE_FILENAME);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
