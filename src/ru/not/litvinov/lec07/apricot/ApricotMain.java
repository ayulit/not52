package ru.not.litvinov.lec07.apricot;


import ru.not.litvinov.lec07.apricot.fruits.Fruit;
import ru.not.litvinov.lec07.apricot.vegs.Veg;

import java.util.ArrayList;
import java.util.List;

import static ru.not.litvinov.lec07.apricot.WarehouseType.FRUIT;
import static ru.not.litvinov.lec07.apricot.WarehouseType.VEG;


public class ApricotMain {
    public static void main(String[] args) {
        List<Veg> vegCargo = new ArrayList<>(10);
        List<Fruit> fruitCargo = new ArrayList<>(10);

        Warehouse fruitWarehouseA = new Warehouse(FRUIT);
        Warehouse fruitWarehouseB = new Warehouse(FRUIT);
        Warehouse fruitWarehouseC = new Warehouse(FRUIT);
        Warehouse vegWarehouseA = new Warehouse(VEG);
        Warehouse vegWarehouseB = new Warehouse(VEG);
        Warehouse vegWarehouseC = new Warehouse(VEG);

        fruitWarehouseA.loadCargo(fruitCargo);
        fruitWarehouseB.loadCargo(fruitCargo);
        fruitWarehouseC.loadCargo(fruitCargo);

        vegWarehouseA.loadCargo(vegCargo);
        vegWarehouseB.loadCargo(vegCargo);
        vegWarehouseC.loadCargo(vegCargo);

//        vegWarehouseA.loadCargo(fruitCargo); // FIXME: ???

        Shop shop = new Shop();
        shop.printCargo(vegCargo);
        shop.printCargo(fruitCargo);
    }
}
