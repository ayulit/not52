package ru.not.litvinov.lec04.sold.goods.tools;

import ru.not.litvinov.lec04.sold.goods.Tool;

public class FretSawTool extends Tool {
    private int depth;

    public FretSawTool(int price, String name, int number, int power, int depth) {
        super(price, name, number, power);
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "Лобзик " + super.toString() +
                "глубина " + depth + "мм ";
    }
}
