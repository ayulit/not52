package ru.not.litvinov.lec04.sold.goods.tools;

import ru.not.litvinov.lec04.sold.goods.Returnable;
import ru.not.litvinov.lec04.sold.goods.Tool;

public class SawTool extends Tool implements Returnable {
    private int radius;

    public SawTool(int price, String name, int number, int power, int radius) {
        super(price, name, number, power);
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Дисковая пила " + super.toString() +
                "диск " + radius + "мм ";
    }
}
