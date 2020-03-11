package ru.not.litvinov.lec04.hide;

public class Player {
    private String name;
    private Condition condition;

    public Player(char name, Condition condition) {
        this.name = String.valueOf(name);
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", condition=" + condition +
                '}';
    }
}
