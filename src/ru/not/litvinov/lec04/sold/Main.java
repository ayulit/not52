package ru.not.litvinov.lec04.sold;

import ru.not.litvinov.lec04.sold.goods.Computer;
import ru.not.litvinov.lec04.sold.goods.Returnable;
import ru.not.litvinov.lec04.sold.goods.Tool;
import ru.not.litvinov.lec04.sold.goods.computers.DesktopComputer;
import ru.not.litvinov.lec04.sold.goods.computers.LaptopComputer;
import ru.not.litvinov.lec04.sold.goods.tools.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Good> goods = Arrays.asList(
                new FretSawTool(3350, "Makita 4329", 1, 450, 65),
                new FretSawTool(2905, "Hyundai J 800", 2, 710, 80),

                new DrillTool(2420, "ДИОЛД МЭС-5-03", 3, 800, 32),
                new DrillTool(1590, "Hitachi DV16SS", 4, 600, 20),

                new SawTool(4480, "Интерскол ДП-190/1600М", 5, 1600, 190),
                new SawTool(7749, "Makita HS7601", 6, 1200, 190),

                new LaptopComputer(64490, "Apple MacBook Air 13", 7, 8, 128, 2, 13, 12),
                new LaptopComputer(80495, "HP OMEN 15-ce000", 8, 12, 1128,  4, 15, 11),

                new DesktopComputer(62200, "Apple Mac Mini MRTR2RU/A", 9, 8, 128, 4),
                new DesktopComputer(61070, "HP Omen 875-0013ur", 10, 16, 1128, 4)
        );

        int income = 0;
        Good maxPowerTool = new FretSawTool(0, "", 0, 0, 0);
        int totalHdd = 0;
        for (Good good: goods) {
            income += good.getPrice() * good.getNumber();
            System.out.println(good);

            if (good instanceof Tool) {
                maxPowerTool = ((Tool) good).getPower() > ((Tool) maxPowerTool).getPower() ? good: maxPowerTool;
            }

            if (good instanceof Computer) {
                totalHdd += ((Computer)good).getHdd();
            }
        }

        System.out.println("\nGross income=" + income);
        System.out.println("Max Power Tool: " + maxPowerTool);
        System.out.println("Total HDD space=" + totalHdd);

        System.out.println("\nNext goods can be returned:\n");
        for (Good good: goods) {
            if (good instanceof Returnable) {
                System.out.print(good);
                System.out.print("| Return in " + ((Returnable) good).getReturnDays() + " days. ");
                System.out.println("Documents: " + ((Returnable) good).getDocuments());
            }
        }
    }
}
