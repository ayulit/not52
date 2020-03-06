package ru.not.litvinov.task3;

public class Task3 {
    public static void main(String[] args) {

        MailBox mailBox = new MailBox();

        Person a = new Person("John", "Doe");
        Person b = new Person("Jane", "Doe");

        PaperMessage m1 = new PaperMessage(a, b, "Hello", 1, 2);
        PaperMessage m2 = new PaperMessage(b, a, "Hi", 2, 1);
        PaperMessage m3 = new PaperMessage(b, a, "Namaste", 2, 1);
        PaperMessage m4 = new PaperMessage(b, a, "Extra", 2, 1);

        mailBox.addMail(m1);
        mailBox.addMail(m2);
        mailBox.addMail(m3);

        for (PaperMessage m: mailBox.getMails()) {
            if (m != null) {
                System.out.println(m.getMessage());
            }
        }

        mailBox.addMail(m4);

        for (PaperMessage m: mailBox.getMails()) {
            if (m != null) {
                System.out.println(m.getMessage());
            }
        }
    }
}
