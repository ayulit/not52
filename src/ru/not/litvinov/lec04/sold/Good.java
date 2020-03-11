package ru.not.litvinov.lec04.sold;

public abstract class Good {
    private int price;
    private String name;
    private int number;

    public Good(int price, String name, int number) {
        this.price = price;
        this.name = name;
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return name + " " +
                price + " " +
                number + "шт ";
    }
}
