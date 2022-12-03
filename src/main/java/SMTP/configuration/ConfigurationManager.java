package SMTP.configuration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Florian Conti
 * @author Patrick Furrer
 * Class used to configure the program. in particular, the address and the port of the server as well as the utf8 files
 */
public class ConfigurationManager {

    private final String smtpServerAddress;
    private final int smtpServerPort;
    private final int numberOfGroups;

    String currentProjectFilePath = System.getProperty("user.dir");

    String configFilePath   = currentProjectFilePath + "/DAI-2022-SMTP-Classe-B/config/config.properties";
    String messagesFilePath = currentProjectFilePath + "/DAI-2022-SMTP-Classe-B/config/messages.utf8";
    String victimsFilePath  = currentProjectFilePath + "/DAI-2022-SMTP-Classe-B/config/victims.utf8";


    /**
     * Method used to configure the properties stored in the config.properties file
     * @throws IOException if error
     */
    public ConfigurationManager() throws IOException {
        Properties property = new Properties();

        BufferedReader br = new BufferedReader(new FileReader(configFilePath, StandardCharsets.UTF_8));

        property.load(br);

        this.smtpServerAddress = property.getProperty("smtpServerAddress");
        this.smtpServerPort = Integer.parseInt(property.getProperty("smtpServerPort"));
        this.numberOfGroups = Integer.parseInt(property.getProperty("numberOfGroups"));
    }

    /**
     * Method used to get the number of groups defined in the config file
     * @return the number of groups
     */
    public int getNumberOfGroups() { return numberOfGroups; }

    /**
     * Method used to get the server address defined in the config file
     * @return the server address
     */
    public String getSmtpServerAddress() { return smtpServerAddress; }

    /**
     * Method used to get the server port defined in the config file
     * @return the server port
     */
    public int getSmtpServerPort() { return smtpServerPort; }

    /**
     * Method used to store all victim email addresses stored in the victims.utf8 file
     * @return a list of email addresses
     */
    public List<String> getMailFromFile() {
        List<String> emails = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(victimsFilePath));

            String line;
            while((line = br.readLine()) != null) {
                emails.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return emails;
    }

    /**
     * Method used to store all the messages stored in the messages.utf8 who will be send to the victims
     * @return a list of messages
     */
    public List<String> getMessagesFromFile() {
        List<String> messages = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(messagesFilePath),
                    StandardCharsets.UTF_8));

            String line;
            StringBuilder stringbuilder = new StringBuilder();

            while((line = br.readLine()) != null) {
                if(line.startsWith("------")) {
                    messages.add(stringbuilder.toString());
                    stringbuilder = new StringBuilder();
                } else {
                    stringbuilder.append(line).append("\n");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return messages;
    }

}
