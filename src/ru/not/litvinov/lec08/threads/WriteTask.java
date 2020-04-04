package ru.not.litvinov.lec08.threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteTask implements Runnable {

    private int idx;
    private String path;
    private Container container;

    public WriteTask(Container container, int idx, String path) {
        this.idx = idx;
        this.path = path;
        this.container = container;
    }


    @Override
    public void run() {
        File file = new File(path + (idx +1));
        try(PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(container.get(idx));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}