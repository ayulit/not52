package ru.not.litvinov.task3;

import java.time.Instant;

public final class EmailMessage extends Message {
    final Instant sendDate;
    final Instant receiveDate;

    public EmailMessage(Person from, Person to, String message, Instant sendDate, Instant receiveDate) {
        super(from, to, message);
        this.sendDate = sendDate;
        this.receiveDate = receiveDate;
    }

    public Instant getSendDate() {
        return sendDate;
    }

    public Instant getReceiveDate() {
        return receiveDate;
    }
}
