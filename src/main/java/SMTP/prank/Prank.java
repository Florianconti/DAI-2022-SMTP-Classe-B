package SMTP.prank;

import SMTP.mail.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Prank {

    private final List<Person> victims = new ArrayList<>();
    private Person sender;
    private String message;


    public void setSender(Person sender) {
        this.sender = sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addVictims(List<Person> victimList){
        victims.addAll(victimList);
    }

    public Message generateMailMessage(){
        Message message = new Message();

        message.setContent(this.message + "\r\n" + sender.getFirstName());

        String[] to = victims.stream().map(Person::getMail).collect(Collectors.toList()).toArray(new String[]{});
        message.setTo(to);

        message.setFrom(sender.getMail());

        return message;
    }

}
