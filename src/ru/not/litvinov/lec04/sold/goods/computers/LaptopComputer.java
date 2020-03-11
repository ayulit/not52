package ru.not.litvinov.lec04.sold.goods.computers;

import ru.not.litvinov.lec04.sold.goods.Computer;

public class LaptopComputer extends Computer {
    private int size;
    private int performance;

    public LaptopComputer(int price, String name, int number, int ram, int hdd, int core, int size, int performance) {
        super(price, name, number, ram, hdd, core);
        this.size = size;
        this.performance = performance;
    }

    @Override
    public String toString() {
        return "Ноутбук " + super.toString() +
                "размер " + size + "\" " +
                "аккумулятор " + performance + "ч ";
    }
}
