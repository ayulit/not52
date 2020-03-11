package ru.not.litvinov.lec04.fire;

import java.util.Random;

public class FireMain {
    public static void main(String[] args) {
        Random rand = new Random(System.currentTimeMillis()); // initialization of randomizer

        Season randomSeason = Season.values()[rand.nextInt(4)];
        Turtle randomTurtle = Turtle.values()[rand.nextInt(4)];

        System.out.println(randomSeason + " " + randomSeason.getGame());
        System.out.println(randomTurtle + " " + randomTurtle.getGame());

        if(randomTurtle.getGame() == randomSeason.getGame()) {
            System.out.println("Wins!");
        } else {
            System.out.println("Fails!");
        }

    }
}
