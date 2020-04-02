package ru.not.litvinov.lec07.apricot;

import ru.not.litvinov.lec07.apricot.fruits.Fruit;
import ru.not.litvinov.lec07.apricot.vegs.Veg;

import java.util.ArrayList;
import java.util.List;

public class ApricotMain {
    public static void main(String[] args) {
        List<Veg> vegCargo = new ArrayList<>(10);
        List<Fruit> fruitCargo = new ArrayList<>(10);

        Warehouse<Fruit> fruitWarehouseA = new Warehouse<>(new FruitGenerator());
        Warehouse<Fruit> fruitWarehouseB = new Warehouse<>(new FruitGenerator());
        Warehouse<Fruit> fruitWarehouseC = new Warehouse<>(new FruitGenerator());
        Warehouse<Veg> vegWarehouseA = new Warehouse<>(new VegGenerator());
        Warehouse<Veg> vegWarehouseB = new Warehouse<>(new VegGenerator());
        Warehouse<Veg> vegWarehouseC = new Warehouse<>(new VegGenerator());

        fruitWarehouseA.loadCargo(fruitCargo);
        fruitWarehouseB.loadCargo(fruitCargo);
        fruitWarehouseC.loadCargo(fruitCargo);

        vegWarehouseA.loadCargo(vegCargo);
        vegWarehouseB.loadCargo(vegCargo);
        vegWarehouseC.loadCargo(vegCargo);

//        vegWarehouseA.loadCargo(fruitCargo); // truly won't work

        Shop shop = new Shop();
        shop.printCargo(vegCargo);
        shop.printCargo(fruitCargo);
    }
}
