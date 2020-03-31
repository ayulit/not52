package ru.not.litvinov.lec07.apricot.fruits;

public class Apricot implements Fruit {
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
