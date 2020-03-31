package ru.not.litvinov.lec07.apricot.fruits;

public class Orange implements Fruit {
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
