package ru.not.litvinov.lec07.apricot;

import ru.not.litvinov.lec07.apricot.vegs.Tomato;
import ru.not.litvinov.lec07.apricot.vegs.Veg;

public class VegGenerator implements PlantGenerator {
    @Override
    public Veg generatePlant() {
        return new Tomato();
    }
}
