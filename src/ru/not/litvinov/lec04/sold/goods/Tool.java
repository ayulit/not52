package ru.not.litvinov.lec04.sold.goods;

import ru.not.litvinov.lec04.sold.Good;

public abstract class Tool extends Good {
    private int power;

    public Tool(int price, String name, int number, int power) {
        super(price, name, number);
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return super.toString() +
                "мощность " + power + " Вт ";
    }
}
