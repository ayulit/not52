package ru.not.litvinov.lec07.apricot;

import ru.not.litvinov.lec07.apricot.fruits.Fruit;
import ru.not.litvinov.lec07.apricot.vegs.Veg;
import ru.not.litvinov.lec07.apricot.warehouse.FruitFactory;
import ru.not.litvinov.lec07.apricot.warehouse.VegFactory;
import ru.not.litvinov.lec07.apricot.warehouse.Warehouse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ApricotMain {
    public static void main(String[] args) {
        List<Veg> vegCargo = new ArrayList<>(10);
        List<Fruit> fruitCargo = new ArrayList<>(10);

        List<Warehouse<Fruit>> fruitWhs = new LinkedList<>();
        List<Warehouse<Veg>> vegWhs = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            fruitWhs.add(new Warehouse<>(new FruitFactory()));
            vegWhs.add(new Warehouse<>(new VegFactory()));
        }

        for (int i = 0; i < 10; i++) {
            fruitWhs.get(i).loadCargo(fruitCargo);
            vegWhs.get(i).loadCargo(vegCargo);
        }

        Shop shop = new Shop();
        shop.printCargo(vegCargo);
        shop.printCargo(fruitCargo);
    }
}
