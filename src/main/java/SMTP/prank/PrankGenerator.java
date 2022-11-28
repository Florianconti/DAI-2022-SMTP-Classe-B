package SMTP.prank;

import SMTP.mail.Group;
import SMTP.mail.Person;
import SMTP.configuration.ConfigurationManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class PrankGenerator {

    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());
    private final ConfigurationManager configurationManager;

    public PrankGenerator(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    public List<Prank> generatePranks(){

        List<Prank> pranks = new ArrayList<>();
        List<String> messages = configurationManager.getMessagesFromFile();

        int numberOfGroups = configurationManager.getNumberOfGroups();
        int numberOfVictims = configurationManager.getMailFromFile().size();

        if(numberOfVictims / numberOfGroups < 3){
            numberOfGroups = numberOfVictims / 3;
            LOG.warning("There are not enough victims to generate the desired number of groups.");
        }

        List<Group> groups = generateGroups(configurationManager.getMailFromFile(), numberOfGroups);
        for(Group group : groups){
            Prank prank = new Prank();

            List<Person> victims = group.getPersons();
            Collections.shuffle(victims);
            Person sender = victims.remove(0);
            prank.setSender(sender);
            prank.addVictims(victims);

            Random rand = new Random();
            String message = messages.get(rand.nextInt(messages.size()));
            prank.setMessage(message);

            pranks.add(prank);
        }
        return pranks;
    }

    private List<Group> generateGroups(List<String> victimsMail, int numberOfGroups){
        List<String> availableVictims = new ArrayList<>(victimsMail);
        Collections.shuffle(availableVictims);
        List<Group> groups = new ArrayList<>();
        for(int i = 0; i < numberOfGroups; i++){
            Group group = new Group();
            groups.add(group);
        }
        int turn = 0;
        Group targetGroup;
        while(availableVictims.size() > 0){
            targetGroup = groups.get(turn);
            turn = (turn + 1) % groups.size();
            Person victim = new Person(availableVictims.remove(0));
            targetGroup.addPerson(victim);
        }
        return groups;
    }
}
