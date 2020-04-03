package ru.not.litvinov.lec07.apricot;

import ru.not.litvinov.lec07.apricot.fruits.Fruit;
import ru.not.litvinov.lec07.apricot.vegs.Veg;
import ru.not.litvinov.lec07.apricot.warehouse.FruitFactory;
import ru.not.litvinov.lec07.apricot.warehouse.VegFactory;
import ru.not.litvinov.lec07.apricot.warehouse.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class ApricotMain {
    public static void main(String[] args) {
        List<Veg> vegCargo = new ArrayList<>(10);
        List<Fruit> fruitCargo = new ArrayList<>(10);

        Warehouse<Fruit> fruitWarehouseA = new Warehouse<>(new FruitFactory());
        Warehouse<Fruit> fruitWarehouseB = new Warehouse<>(new FruitFactory());
        Warehouse<Fruit> fruitWarehouseC = new Warehouse<>(new FruitFactory());
        Warehouse<Veg> vegWarehouseA = new Warehouse<>(new VegFactory());
        Warehouse<Veg> vegWarehouseB = new Warehouse<>(new VegFactory());
        Warehouse<Veg> vegWarehouseC = new Warehouse<>(new VegFactory());

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
