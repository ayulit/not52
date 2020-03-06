package ru.not.litvinov.task3;

public class MailBox {
    private int paperMsgCapacity = 3;
    private int emailMsgCapacity = 3;

    PaperMessage[] mails = new PaperMessage[paperMsgCapacity];
    EmailMessage[] emails = new EmailMessage[emailMsgCapacity];
    int i = 0;
    int j = 0;

    public void addMail(PaperMessage message) {
        if (i >= paperMsgCapacity) {
            paperMsgCapacity *= 2;

            PaperMessage[] src = mails;
            PaperMessage[] dest = new PaperMessage[paperMsgCapacity];

            System.arraycopy(src, 0, dest, 0, src.length);

            mails = dest;
        }

        mails[i++] = message;
    }

    public void addEmail(EmailMessage message) {
        /* just the same */
    }

    public PaperMessage[] getMails() {
        return mails;
    }

    public EmailMessage[] getEmails() {
        return emails;
    }
}
