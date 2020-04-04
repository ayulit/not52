package ru.not.litvinov.lec08.threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadTask extends Thread {

    private File file;
    private String result = "";

    public ReadTask(File file) {
        this.file = file;
    }

    public String getResult() {
        return result;
    }

    @Override
    public void run() {
        try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {
            result = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
