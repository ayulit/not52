package ru.not.litvinov.task3;

public final class PaperMessage extends Message {
    final Integer fromPostalIndex;
    final Integer toPostalIndex;

    public PaperMessage(Person from, Person to, String message, Integer fromPostalIndex, Integer toPostalIndex) {
        super(from, to, message);
        this.fromPostalIndex = fromPostalIndex;
        this.toPostalIndex = toPostalIndex;
    }

    public Integer getFromPostalIndex() {
        return fromPostalIndex;
    }

    public Integer getToPostalIndex() {
        return toPostalIndex;
    }
}
