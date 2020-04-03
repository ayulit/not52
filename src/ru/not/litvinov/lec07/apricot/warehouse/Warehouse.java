package ru.not.litvinov.lec07.apricot.warehouse;

import ru.not.litvinov.lec07.apricot.Plant;

import java.util.List;
import java.util.Random;

public class Warehouse<T extends Plant> {

    private PlantFactory plantFactory;
    private boolean isLoadMore = true;
    private Random random = new Random(System.currentTimeMillis());

    public Warehouse(PlantFactory plantFactory) {
        this.plantFactory = plantFactory;
    }

    public void loadCargo(List<T> cargo) {
        while (cargo.size() <= 10 || isLoadMore) {
            cargo.add((T) plantFactory.generatePlant());
            isLoadMore = random.nextBoolean();
        }
    }
}



