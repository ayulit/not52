package ru.not.litvinov.lec04.fire;

public enum Season implements Gameable {
    WINTER(Game.SNOWBALLS),
    SPRING(Game.SHIPS),
    SUMMER(Game.SUNDOGS),
    FALL(Game.BOOT);

    private final Game game;

    Season(Game game) {
        this.game = game;
    }

    @Override
    public Game getGame() {
        return game;
    }
}
