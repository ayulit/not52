package ru.not.litvinov.lec05.realtor;

import ru.not.litvinov.lec05.realtor.list.District;
import ru.not.litvinov.lec05.realtor.dto.Flat;
import ru.not.litvinov.lec05.realtor.dto.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Database {

    private Flat[] flats;

    public void autofill(int flatsNumber) {
        flats = new Flat[flatsNumber];

        Random random = new Random(System.currentTimeMillis());

        List<String> firstnames = new ArrayList<>();
        List<String> lastnames = new ArrayList<>();
        // FIXME: how to open file in current project
        try(FileReader fr = new FileReader("names.txt"); BufferedReader br = new BufferedReader(fr);) {
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] name = line.split("\\s+");
                firstnames.add(name[0]);
                lastnames.add(name[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < flats.length; i++) {
            Person person = new Person();
            Flat flat = new Flat();

            String name = firstnames.get(random.nextInt(firstnames.size())) + " " + lastnames.get(random.nextInt(lastnames.size()));

            String randomPhoneNumber = "89" + (random.nextInt(90) + 10) + (random.nextInt(9000000) + 1000000);
            String phone = random.nextBoolean() ? randomPhoneNumber : "" ; // yes | no

            District[] districts = District.values();

            String district = districts[random.nextInt(districts.length)].getDistrict();
            int roomNumber = random.nextInt(5) + 1;
            int square = random.nextInt(200) + 33;
            int price = 76346 * square;

            person.setName(name);
            person.setPhone(phone);

            flat.setDistrict(district);
            flat.setRoomNumber(roomNumber);
            flat.setSquare(square);
            flat.setPrice(price);
            flat.setOwner(person);

            flats[i] = flat;
        }
    }

    public void manualfill() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Please, enter number of flats:");
            flats = new Flat[sc.nextInt()];

            for (int i = 0; i < flats.length; i++) {
                Person person = new Person();
                Flat flat = new Flat();

                String name = "";
                String phone = "";
                String district = "";
                int roomNumber = 0;
                int square = 0;
                int price = 0;

                System.out.println("Please, enter info about flat #" + (i+1) + " (for exit type 'exit').");

                System.out.println("Select district:");
                for (District d: District.values()) {
                    System.out.println(d.getIndex() + " - " + d.getDistrict());
                }
                int districtNum = Integer.parseInt(processInput(sc)) - 1;
                district = District.values()[districtNum].getDistrict();

                System.out.println("Enter roomNumber: ");
                roomNumber = Integer.parseInt(processInput(sc));

                System.out.println("Enter square: ");
                square = Integer.parseInt(processInput(sc));

                System.out.println("Enter price: ");
                price = Integer.parseInt(processInput(sc));

                System.out.println("Please, enter following information about owner...");

                System.out.println("Enter name: ");
                name = processInput(sc);

                System.out.println("Enter phone: ");
                phone = processInput(sc);

                person.setName(name);
                person.setPhone(phone);

                flat.setDistrict(district);
                flat.setRoomNumber(roomNumber);
                flat.setSquare(square);
                flat.setPrice(price);
                flat.setOwner(person);

                flats[i] = flat;
            }
        } catch (InterruptedException e) {
            System.out.println("Goodbye...");
        }
    }

    private String processInput(Scanner sc) throws InterruptedException {
        String input = sc.next();
        if ("exit".equals(input)) {
            throw new InterruptedException();
        }
        return input;
    }

    public void serialize() {
        String filename = "flat.ser";

        try (FileOutputStream fos = new FileOutputStream(filename); ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(flats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
