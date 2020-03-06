package ru.not.litvinov.task3;

public abstract class Message {
    final Person from;
    final Person to;
    final String message;

    protected Message(Person from, Person to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public Person getFrom() {
        return from;
    }

    public Person getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }
}
