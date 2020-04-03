package ru.not.litvinov.lec07.apricot;

import java.util.List;

public class Shop {
    public <P extends Plant> void printCargo(List<P> cargo) {
        int i = 1;
        for (P plant: cargo) {
            System.out.println(i++ + ". " + plant.getName());
        }
        System.out.println();
    }
}
