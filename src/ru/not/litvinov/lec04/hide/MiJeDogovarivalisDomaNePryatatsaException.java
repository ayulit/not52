package ru.not.litvinov.lec04.hide;

public class MiJeDogovarivalisDomaNePryatatsaException extends Exception {
    private Player player;

    public MiJeDogovarivalisDomaNePryatatsaException(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
