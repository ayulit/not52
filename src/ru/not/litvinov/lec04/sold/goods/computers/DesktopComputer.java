package ru.not.litvinov.lec04.sold.goods.computers;

import ru.not.litvinov.lec04.sold.goods.Computer;

public class DesktopComputer extends Computer {
    public DesktopComputer(int price, String name, int number, int ram, int hdd, int core) {
        super(price, name, number, ram, hdd, core);
    }

    @Override
    public String toString() {
        return "Компьютер " + super.toString();
    }
}
