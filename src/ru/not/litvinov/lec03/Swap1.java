package ru.not.litvinov.lec03;

public class Swap1 {
    public static void main(String[] args) {
        int one = 1;
        int two = 2;

        int extra = one;
        one = two;
        two = extra;

        System.out.println("one=" + one); // 2
        System.out.println("two=" + two); // 1
    }
}
