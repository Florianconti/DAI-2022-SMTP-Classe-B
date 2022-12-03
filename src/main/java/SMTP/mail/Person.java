package SMTP.mail;

import java.util.regex.Pattern;

/**
 * @author Florian Conti
 * @author Patrick Furrer
 * Class used to define a person
 */
public class Person {

    private final String firstName;
    private final String mail;

    /**
     * Constuctor used to create a person
     * @param mail of the person
     */
    public Person(String mail) {
        if (mail.contains("@")) {
            this.mail = mail;

            // For us, the email is in this format : firstName.LastName@mail.com
            String[] test = mail.split(Pattern.quote("."));
            this.firstName = test[0];
        } else {
            System.out.println(mail);
            throw new IllegalArgumentException("Invalid mail address");
        }
    }

    /**
     * Getter of the firstname
     * @return the firstname of a person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter of the mail
     * @return the mail of the person
     */
    public String getMail() {
        return mail;
    }

}
