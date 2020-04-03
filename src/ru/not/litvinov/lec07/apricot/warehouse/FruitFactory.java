package ru.not.litvinov.lec07.apricot.warehouse;

import ru.not.litvinov.lec07.apricot.fruits.Apple;
import ru.not.litvinov.lec07.apricot.fruits.Apricot;
import ru.not.litvinov.lec07.apricot.fruits.Banana;
import ru.not.litvinov.lec07.apricot.fruits.Fruit;
import ru.not.litvinov.lec07.apricot.fruits.Orange;
import ru.not.litvinov.lec07.apricot.fruits.Peach;
import ru.not.litvinov.lec07.apricot.fruits.Pear;
import ru.not.litvinov.lec07.apricot.fruits.Pineapple;

import java.util.Random;

public class FruitFactory implements PlantFactory {
    private static Random random = new Random(System.currentTimeMillis());

    @Override
    public Fruit generatePlant() {
        switch (random.nextInt(7) + 1) {
            case 1:
                return new Apple();
            case 2:
                return new Apricot();
            case 3:
                return new Banana();
            case 4:
                return new Orange();
            case 5:
                return new Peach();
            case 6:
                return new Pear();
            case 7:
            default:
                break;
        }
        return new Pineapple();
    }
}
