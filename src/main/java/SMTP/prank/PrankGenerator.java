package SMTP.prank;

import SMTP.mail.Group;
import SMTP.mail.Person;
import SMTP.configuration.ConfigurationManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Florian Conti
 * @author Patrick Furrer
 */
public class PrankGenerator {

    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());
    private final ConfigurationManager configurationManager;

    /**
     * Constructor
     * @param configurationManager Used to get all the configurations
     */
    public PrankGenerator(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    /**
     * Generate a list of N group of victims
     * @param victimsMail All the mail of the victims
     * @param numberOfGroups Number of groups to create
     * @return A list of groups
     */
    private List<Group> generateGroups(List<String> victimsMail, int numberOfGroups){

        List<String> victims = new ArrayList<>(victimsMail);

        // Shuffle to not send the mail everytime to the same persons
        Collections.shuffle(victims);
        List<Group> groups = new ArrayList<>();

        for(int i = 0; i < numberOfGroups; i++){
            Group group = new Group();
            groups.add(group);
        }

        int turn = 0;
        Group targetGroup;

        // Filling groups
        while(victims.size() > 0){
            targetGroup = groups.get(turn);
            turn = (turn + 1) % groups.size();
            Person victim = new Person(victims.remove(0));
            targetGroup.addPerson(victim);
        }
        return groups;
    }

    /**
     * Generate the prank
     * @return A list of the pranks
     */
    public List<Prank> generatePranks(){

        List<Prank> pranks = new ArrayList<>();
        List<String> messages = configurationManager.getMessagesFromFile();

        int numberVictims = configurationManager.getMailFromFile().size();
        int numberGroups = configurationManager.getNumberOfGroups();

        if(numberVictims / numberGroups < 3){
            numberGroups = numberVictims / 3;
            LOG.warning("There are not enough people to generate a prank");
        }

        List<Group> groups = generateGroups(configurationManager.getMailFromFile(), numberGroups);
        for(Group group : groups){
            Prank prank = new Prank();

            List<Person> victims = group.getPersons();

            // Shuffle to make the sender random
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
}
