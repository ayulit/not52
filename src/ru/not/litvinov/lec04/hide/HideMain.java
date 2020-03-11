package ru.not.litvinov.lec04.hide;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class HideMain {

    private static final int PLAYERS = 10;

    public static void main(String[] args) {

        Player[] players = new Player[PLAYERS];
        int i = 0;
        Random rand = new Random(System.currentTimeMillis());
        for (char playerName = 'a'; playerName < 'a' + PLAYERS ; playerName++) {
            int conditionsNumber = Condition.values().length;
            Condition randomCondition = Condition.values()[rand.nextInt(conditionsNumber)];
            players[i++] = new Player(playerName, randomCondition);
        }

        List<Player> losers = new LinkedList<>();
        List<Player> winners = new LinkedList<>();

        try {
            // Game Loop
            for (Player player: players) {
                System.out.print(player + " ");
                Condition hostExclamation = Condition.values()[rand.nextInt(2)];
                System.out.println(" | Host says: " + hostExclamation);

                Condition playerCondition = player.getCondition();
                if(hostExclamation == Condition.NOT_HID) {
                    if(playerCondition == Condition.NOT_HID) {
                        // host guessed right, player goes to losers team
                        losers.add(player);
                    } else if (playerCondition == Condition.HID) {
                        winners.add(player);
                    }  else if (playerCondition == Condition.GONE) {
                        // FIXME ???
                    }
                } else if (hostExclamation == Condition.HID) {
                    if(playerCondition == Condition.NOT_HID) {
                        winners.add(player);
                    } else if (playerCondition == Condition.HID) {
                        losers.add(player);
                    } else if (playerCondition == Condition.GONE) {
                        throw new MiJeDogovarivalisDomaNePryatatsaException(player);
                    }
                }
            }

            System.out.println("\nWINNERS: ");
            for (Player player : winners) {
                System.out.print(player.getName() + " ");
            }
            System.out.println("\n\nLOSERS: ");
            for (Player player : losers) {
                System.out.print(player.getName() + " ");
            }

        } catch (MiJeDogovarivalisDomaNePryatatsaException e) {
            System.out.println("<" + e.getPlayer().getName() + "> is cheater!");
        }


    }
}
