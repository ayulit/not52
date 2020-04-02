package ru.not.litvinov.lec07.apricot;

import ru.not.litvinov.lec07.apricot.fruits.Apricot;
import ru.not.litvinov.lec07.apricot.fruits.Fruit;
import ru.not.litvinov.lec07.apricot.fruits.Orange;

import java.util.Random;

public class FruitGenerator implements PlantGenerator {

    private static boolean b = false;
    private static Random random = new Random(System.currentTimeMillis());

    @Override
    public Fruit generatePlant() {
        random.nextBoolean();
        if (b) {
            return new Orange();
        } else {
            return new Apricot();
        }


    }
}
