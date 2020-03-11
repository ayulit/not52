package ru.not.litvinov.lec04.sold.goods.tools;

import ru.not.litvinov.lec04.sold.goods.Returnable;
import ru.not.litvinov.lec04.sold.goods.Tool;

public class DrillTool extends Tool implements Returnable {
    private int calibre;

    public DrillTool(int price, String name, int number, int power, int calibre) {
        super(price, name, number, power);
        this.calibre = calibre;
    }

    @Override
    public String toString() {
        return "Дрель " + super.toString() +
                "макс. сверло " + calibre + "мм ";
    }
}
