package ru.not.litvinov.lec07.apricot.warehouse;

import ru.not.litvinov.lec07.apricot.vegs.Carrot;
import ru.not.litvinov.lec07.apricot.vegs.Tomato;
import ru.not.litvinov.lec07.apricot.vegs.Veg;

import java.util.Random;

public class VegFactory implements PlantFactory {
    private static Random random = new Random(System.currentTimeMillis());

    @Override
    public Veg generatePlant() {
        switch (random.nextInt(2) + 1) {
            case 1:
                return new Tomato();
            case 2:
            default:
                break;
        }
        return new Carrot();
    }
}
