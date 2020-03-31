package ru.not.litvinov.lec07.apricot;

import java.util.List;

public class Shop {
    public <P extends Plant> void printCargo(List<P> cargo) {
        for (P plant: cargo) {
            System.out.println(plant.getName());
        }
    }
}
