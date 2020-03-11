package ru.not.litvinov.lec04.sold.goods;

public interface Returnable {

    String RECEIPT = "receipt";
    String PASSPORT = "passport";

    int getReturnDays();
    String getDocuments();
}
