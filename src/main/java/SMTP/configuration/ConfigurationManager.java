package SMTP.configuration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ConfigurationManager {

    private final String smtpServerAddress;
    private final int smtpServerPort;
    private final int numberOfGroups;

    String currentProjectFilePath = System.getProperty("user.dir");

    String configFilePath   = currentProjectFilePath + "/config/config.properties";
    String messagesFilePath = currentProjectFilePath + "/config/messages.utf8";
    String victimsFilePath  = currentProjectFilePath + "/config/victims.utf8";


    public ConfigurationManager() throws IOException {
        Properties property = new Properties();

        BufferedReader br = new BufferedReader(new FileReader(configFilePath, StandardCharsets.UTF_8));

        property.load(br);

        this.smtpServerAddress = property.getProperty("smtpServerAddress");
        this.smtpServerPort = Integer.parseInt(property.getProperty("smtpServerPort"));
        this.numberOfGroups = Integer.parseInt(property.getProperty("numberOfGroups"));
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public int getSmtpServerPort() { return smtpServerPort;}

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

    public List<String> getMessagesFromFile() {
        List<String> messages = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(messagesFilePath), StandardCharsets.UTF_8));

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
