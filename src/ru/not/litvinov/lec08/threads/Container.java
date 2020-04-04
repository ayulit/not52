package ru.not.litvinov.lec08.threads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Container {
    private Random random = new Random(System.currentTimeMillis());

    private List<String> listOfStrings;
    private int capacity;

    public Container(int capacity) {
        this.capacity = capacity;
        this.listOfStrings = new ArrayList<>(capacity);
    }

    public void autofill(int stringLen) {
        for (int i = 0; i < capacity ; i++) {
            String srt = (i + 1) + ". " + generateString(stringLen);
            listOfStrings.add(srt);
        }
    }

    public void print() {
        Iterator it = listOfStrings.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public String get(int i) {
        return listOfStrings.get(i);
    }

    public boolean add(String s) {
        return listOfStrings.add(s);
    }

    private String generateString(int len) {
        StringBuilder stringBuilder = new StringBuilder(len);
        int zCode = 'z';
        int aCode = 'a';
        char randomChar;

        for (int i = 0; i < len ; i++) {
            randomChar = (char) (random.nextInt(zCode - aCode + 1) + aCode);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }


}
