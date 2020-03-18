package ru.not.litvinov.lec05.realtor.list;

public enum Filter {
    ALL("all"),
    DISTRICT("district"),
    ROOM_NUMBER("room number"),
    SQUARE("square"),
    PRICE("price");

    private String filter;

    Filter(String filter) {
        this.filter = filter;
    }

    public String getFilter() {
        return filter;
    }
}
