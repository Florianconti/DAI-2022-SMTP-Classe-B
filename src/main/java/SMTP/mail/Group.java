package SMTP.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Florian Conti
 * @author Patrick Furrer
 * Class used to define a group of persons
 */
public class Group {

    private final List<Person> persons = new ArrayList<>();

    /**
     * Method used to add a person in a group
     * @param person to add
     */
    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * Method used to get a list of persons of a group
     * @return the list of persons
     */
    public List<Person> getPersons() {
        return new ArrayList<>(persons);
    }
}
