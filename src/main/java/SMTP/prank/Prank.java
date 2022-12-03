package SMTP.prank;

import SMTP.mail.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Florian Conti
 * @author Patrick Furrer
 * Class allowing to manage the prank
 */
public class Prank {

    private final List<Person> victims = new ArrayList<>();
    private Person sender;
    private String message;


    /**
     * Set the sender of the mail
     * @param sender Victim sender
     */
    public void setSender(Person sender) {
        this.sender = sender;
    }

    /**
     * Set the message of the prank
     * @param message Message of the prank
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Add victim to the list of victims
     * @param victimList List of victims
     */
    public void addVictims(List<Person> victimList){
        victims.addAll(victimList);
    }

    /**
     * Generate the message that will be send
     * @return the message to send
     */
    public Message generateMailMessage(){
        Message message = new Message();

        message.setFrom(sender.getMail());

        String[] to = victims.stream().map(Person::getMail).collect(Collectors.toList()).toArray(new String[]{});
        message.setTo(to);

        message.setContent(this.message + "\r\n" + sender.getFirstName());

        return message;
    }

}
