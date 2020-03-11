package ru.not.litvinov.lec04.fire;

public enum Turtle implements Gameable {
    LEONARDO(Game.BOOT),
    MICHELANGELO(Game.SUNDOGS),
    DONATELLO(Game.SHIPS),
    RAPHAEL(Game.SNOWBALLS);

    private final Game game;

    Turtle(Game game) {
        this.game = game;
    }

    @Override
    public Game getGame() {
        return game;
    }
}
