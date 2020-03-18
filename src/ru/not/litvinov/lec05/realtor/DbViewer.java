package ru.not.litvinov.lec05.realtor;

import ru.not.litvinov.lec05.realtor.list.District;
import ru.not.litvinov.lec05.realtor.list.Filter;
import ru.not.litvinov.lec05.realtor.dto.Flat;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class DbViewer {
    public static void main(String[] args) {
        String filename = "flat.ser";
        Flat[] flats = null;

        try (FileInputStream fos = new FileInputStream(filename); ObjectInputStream in = new ObjectInputStream(fos)) {
            flats = (Flat[]) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Do you want to hide records without phone? (y/n):");
            boolean hideEmptyPhones =  "y".equals(sc.next());

            System.out.println("Please, select option to filter the data:");
            System.out.println("    0 - view all");
            System.out.println("    1 - by district");
            System.out.println("    2 - by room number");
            System.out.println("    3 - by square");
            System.out.println("    4 - by price");

            Filter inputFilter = Filter.values()[sc.nextInt()];
            int[] range;
            switch (inputFilter) {
                case DISTRICT:
                    System.out.println("Please, select district:");
                    for (District district: District.values()) {
                        System.out.println(district.getIndex() + " - " + district.getDistrict());
                    }
                    int districtNum = sc.nextInt() - 1;
                    District district = District.values()[districtNum];
                    for (Flat flat: flats) {
                        if (flat.getDistrict().equals(district.getDistrict())) {
                            if (hideEmptyPhones && flat.getOwner().getPhone().isEmpty()) {

                            } else {
                                System.out.println(flat);
                            }
                        }
                    }
                    break;
                case ROOM_NUMBER:
                    System.out.print("Please, select number of rooms:");
                    int roomsNum = sc.nextInt();
                    for (Flat flat: flats) {
                        if (flat.getRoomNumber() == roomsNum) {
                            if (hideEmptyPhones && flat.getOwner().getPhone().isEmpty()) {

                            } else {
                                System.out.println(flat);
                            }
                        }
                    }
                    break;
                case SQUARE:
                    range = acquireRange(sc);
                    for (Flat flat: flats) {
                        int square = flat.getSquare();
                        if ((square <= range[1]) && (square >= range[0])) {
                            if (hideEmptyPhones && flat.getOwner().getPhone().isEmpty()) {

                            } else {
                                System.out.println(flat);
                            }
                        }
                    }
                    break;
                case PRICE:
                    range = acquireRange(sc);
                    for (Flat flat: flats) {
                        int price = flat.getPrice();
                        if ((price <= range[1]) && (price >= range[0])) {
                            if (hideEmptyPhones && flat.getOwner().getPhone().isEmpty()) {

                            } else {
                                System.out.println(flat);
                            }
                        }
                    }
                    break;
                case ALL:
                default:
                    for (Flat flat: flats) {
                        if (hideEmptyPhones && flat.getOwner().getPhone().isEmpty()) {

                        } else {
                            System.out.println(flat);
                        }
                    }
            }
        }
    }

    private static int[] acquireRange(Scanner sc) {
        int[] range = new int[2];
        System.out.println("Please, select range...");
        System.out.print(" Enter MIN:");
        range[0] = sc.nextInt();
        System.out.print(" Enter MAX:");
        range[1] = sc.nextInt();
        return range;
    }
}
