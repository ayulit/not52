package ru.not.litvinov.chatbot.history;

import java.util.LinkedList;
import java.util.List;

public class History {
    private List<String> lines;

    public History() {
        lines = new LinkedList<>();
    }

    public List<String> getLines() {
        return lines;
    }

    public void addLine(String line) {
        lines.add(line);
    }
}
