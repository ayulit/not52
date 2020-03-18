package ru.not.litvinov.lec05.realtor;

public class DbWriter {
    public static void main(String[] args) {
        Database db = new Database();
        db.manualfill();
//        db.autofill(10);
        db.serialize();
    }
}
