package ru.not.litvinov.lec07.apricot;

import java.util.List;
import java.util.Random;

public class Warehouse<T extends Plant> {

    private PlantGenerator plantGenerator;
    private boolean isLoadMore = true;
    private Random random = new Random(System.currentTimeMillis());

    public Warehouse(PlantGenerator plantGenerator) {
        this.plantGenerator = plantGenerator;
    }

    public void loadCargo(List<T> cargo) {
        while (cargo.size() <= 10 || isLoadMore) {
            cargo.add((T) plantGenerator.generatePlant());
            isLoadMore = random.nextBoolean();
        }
    }
}



