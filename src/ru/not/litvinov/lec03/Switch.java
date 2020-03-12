package ru.not.litvinov.lec03;

public class Switch {
    public static void main(String[] args) {
        int i = 3;
        switch (i) {
            default:
                System.out.println("default");
            case 1:
                System.out.println("1");
            case 2:
                System.out.println("2");
        }
    }
}

// Guess:  default 1 2 YES!
