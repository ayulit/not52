package ru.not.litvinov.lec07.apricot;

import ru.not.litvinov.lec07.apricot.fruits.Fruit;
import ru.not.litvinov.lec07.apricot.fruits.Orange;
import ru.not.litvinov.lec07.apricot.vegs.Tomato;
import ru.not.litvinov.lec07.apricot.vegs.Veg;

import java.util.List;

public class Warehouse {

    WarehouseType type;

    public Warehouse(WarehouseType type) {
        this.type = type;
    }

    public void loadCargo(List<? extends Plant> cargo) {
        switch (type) {
            case VEG:
                while (cargo.size() <= 10) {
                    cargo.add(generateVeg());
                }
                break;
            case FRUIT:
                while (cargo.size() <= 10) {
                    cargo.add(generateFruit());
                }
                break;
        }
    }

    private <K extends Veg> K generateVeg() {
        return (K) new Tomato();
    }

    private <V extends Fruit> V generateFruit() {
        return (V) new Orange();
    }
}



