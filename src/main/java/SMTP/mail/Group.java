package SMTP.mail;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private final List<Person> persons = new ArrayList<>();

    public void addPersons(Person person) {
        persons.add(person);
    }

    public List<Person> getPersons() {
        return new ArrayList<>(persons);
    }

}
