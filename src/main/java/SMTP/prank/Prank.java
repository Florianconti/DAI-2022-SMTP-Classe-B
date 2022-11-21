package SMTP.prank;

import SMTP.mail.Person;
import SMTP.mail.Mail;

import java.util.LinkedList;

public class Prank {

    private LinkedList<Person> victims;
    private final StringBuilder victimsNames = new StringBuilder();
    private Person sender;
    private String message;
    private String subject;

    private String getVictimsMail() {
        StringBuilder str = new StringBuilder();
        for (Person victim : victims) {
            if (str.length() != 0) {
                str.append(", ");
                victimsNames.append(", ");
            }

            victimsNames.append(victim.getFirstName() + " " + victim.getLastName() + "<" + victim.getMail() + ">");
            str.append("<" + victim.getMail() + ">");
        }

        return str.toString();
    }

    private String getvictimsName() {
        return victimsNames.toString();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public void setVictims(LinkedList<Person> victims) {
        this.victims = victims
    }

    private String getMailSender() {
        return "<" + sender.getMail() + ">";
    }

    private String getSenderName() {
        return sender.getFirstName() + " " + sender.getLastName() + getMailSender();
    }

    private String getMailMessage() {
        return message;
    }

    private String getMailSubject() {
        return subject;
    }

    public Mail generateMail() {
        return new Mail(getMailSender(), getSenderName(), getVictimsMail(), getvictimsName(), null, getMailSubject(), getMailMessage());
    }

}
