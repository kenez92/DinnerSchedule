package pl.kenez.model;

public class MailModel {
    private final String mailTo;
    private final String subject;
    private final String content;

    public MailModel(String mailTo, String subject, String content) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.content = content;
    }

    public String getMailTo() {
        return mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}
