package SMTP.configuration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfigurationManager {

    private final String smtpServerAddress;
    private final int smtpServerPort;
    private final boolean smtpAuth;
    private final int numberOfGroups;
    private String smtpUsername;
    private String smtpPassword;

    public ConfigurationManager() throws IOException {
        Properties property = new Properties();

        BufferedReader inputStream = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/config/config.properties", StandardCharsets.UTF_8));

        property.load(inputStream);

        this.smtpServerAddress = property.getProperty("smtpServerAddress");
        this.smtpServerPort = Integer.parseInt(property.getProperty("smtpServerPort"));
        this.numberOfGroups = Integer.parseInt(property.getProperty("numberOfGroups"));
        this.smtpAuth = Boolean.parseBoolean(property.getProperty("smtpAuth"));
        if(smtpAuth) {
            this.smtpUsername = property.getProperty("smtpUsername");
            this.smtpPassword = property.getProperty("smtpPassword");
        }
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public int getSmtpServerPort() { return smtpServerPort;}

    public boolean isSmtpAuthEnabled() { return smtpAuth; }

    public String getSmtpUsername() {return smtpUsername; }

    public String getSmtpPassword() {return smtpPassword; }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

}
