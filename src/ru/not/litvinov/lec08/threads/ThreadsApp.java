package ru.not.litvinov.lec08.threads;

import java.io.File;

public class ThreadsApp {

    private static final int STRINGS = 1001;
    private static final int STR_LEN = 5;

    private static final String SEQUENTAL_PATH = "src/ru/not/litvinov/lec08/threads/strings_sequential/";
    private static final String PARALLEL_PATH = "src/ru/not/litvinov/lec08/threads/strings_parallel/";

    public static void main(String[] args) {

        long start, end;

        Container container = new Container(STRINGS);
        container.autofill(STR_LEN);
//        container.print();

        /* sequential write */
        start = System.currentTimeMillis();
        for (int i = 0; i < STRINGS; i++) {
            (new WriteTask(container, i, SEQUENTAL_PATH)).run();
        }
        end = System.currentTimeMillis();
        System.out.println("Sequential writing time: " + (end - start) + " ms");


        /* parallel write */
        Thread[] writingThreads = new Thread[STRINGS];

        start = System.currentTimeMillis();
        for (int i = 0; i < STRINGS; i++) {
            writingThreads[i] = new Thread(new WriteTask(container, i, PARALLEL_PATH));
            writingThreads[i].start();
        }
        for (int i = 0; i < STRINGS; i++) {
            try {
                writingThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();
        System.out.println("Parallel writing time: " + (end - start) + " ms");

        /* sequential read */
        Container seqReadContainer = new Container(STRINGS);
        File file = new File(SEQUENTAL_PATH);
        File[] files = file.listFiles();

        start = System.currentTimeMillis();
        if (files != null) {
            for(File f : files) {
                ReadTask readTask = new ReadTask(f);
                readTask.run();
                seqReadContainer.add(readTask.getResult());
            }
        }
        end = System.currentTimeMillis();
        System.out.println("Sequential reading time: " + (end - start) + " ms");
//        seqReadContainer.print();

        /* parallel read */
        Container parallelReadContainer = new Container(STRINGS);
        file = new File(PARALLEL_PATH);
        files = file.listFiles();

        start = System.currentTimeMillis();
        if (files != null) {
            ReadTask[] readingThreads = new ReadTask[files.length];
            for (int i = 0; i < files.length; i++) {
                readingThreads[i] = new ReadTask(files[i]);
                readingThreads[i].start();
            }
            for (int i = 0; i < files.length; i++) {
                try {
                    readingThreads[i].join();
                    String result = readingThreads[i].getResult();
                    parallelReadContainer.add(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        end = System.currentTimeMillis();
        System.out.println("Parallel reading time: " + (end - start) + " ms");
//        parallelReadContainer.print();
    }
}


