package SMTP.mail;

import java.util.regex.Pattern;

public class Person {

    private final String firstName;
    private final String mail;

    public Person(String mail) {
        this.mail = mail;

        String[] test = mail.split(Pattern.quote("."));
        this.firstName = test[0];
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMail() {
        return mail;
    }

}
