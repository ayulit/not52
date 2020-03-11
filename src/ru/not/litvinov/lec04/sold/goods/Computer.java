package ru.not.litvinov.lec04.sold.goods;

import ru.not.litvinov.lec04.sold.Good;

public abstract class Computer extends Good {
    private int ram;
    private int hdd;
    private int core;

    public Computer(int price, String name, int number, int ram, int hdd, int core) {
        super(price, name, number);
        this.ram = ram;
        this.hdd = hdd;
        this.core = core;
    }

    public int getHdd() {
        return hdd;
    }

    @Override
    public String toString() {
        return super.toString() +
                "RAM " + ram + "Gb " +
                "HDD " + hdd + "Gb " +
                "ядер CPU " + core + " ";
    }
}
