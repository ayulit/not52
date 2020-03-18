package ru.not.litvinov.lec05.realtor.dto;

import java.io.Serializable;

public class Flat implements Serializable {
    private String district;
    private int roomNumber;
    private int square;
    private int price;
    private Person owner;

    public void setDistrict(String district) {
        this.district = district;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setSquare(int square) {
        this.square = square;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getDistrict() {
        return district;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public int getSquare() {
        return square;
    }
    public int getPrice() {
        return price;
    }
    public Person getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "district='" + district + '\'' +
                ", roomNumber=" + roomNumber +
                ", square=" + square +
                ", price=" + price +
                ", owner=" + owner +
                '}';
    }
}
