package ru.not.litvinov.lec07.apricot.vegs;

public class Tomato implements Veg {
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
