package ru.not.litvinov.chatbot.client;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Client implements Serializable {
    private static final String USER = "YOU";
    private String filename = "presence.ser";

    private ClientPresence presence = ClientPresence.ONLINE;
    private String username = USER;

    public Client() {

        File statusFile = new File (filename);

        if(statusFile.exists()) {
            Client client;
            try (FileInputStream fos = new FileInputStream(filename);
                 ObjectInputStream in = new ObjectInputStream(fos)) {
                client = (Client) in.readObject();

                this.presence = client.getPresence();

            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ClientPresence getPresence() {
        return presence;
    }
    public void setPresence(ClientPresence presence) {
        this.presence = presence;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void serialize() {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
