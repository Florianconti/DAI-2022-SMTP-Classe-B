package SMTP;

import SMTP.configuration.ConfigurationManager;
import SMTP.prank.PrankGenerator;
import SMTP.smtp.SmtpClient;
import SMTP.prank.Prank;

import java.io.IOException;
import java.util.List;

/**
 * @author Florian Conti
 * @author Patrick Furrer
 * Main class to generate and send the pranks
 */
public class SmtpSender
{
    public static void main( String[] args) throws IOException {

        ConfigurationManager configurationManager = new ConfigurationManager();
        PrankGenerator prankGenerator = new PrankGenerator(configurationManager);
        List<Prank> pranks = prankGenerator.generatePranks();

        String smtpServerAddress = configurationManager.getSmtpServerAddress();
        int smtpServerPort = configurationManager.getSmtpServerPort();

        SmtpClient smtp = new SmtpClient(smtpServerAddress, smtpServerPort);

        for(Prank prank : pranks){
            smtp.sendMail(prank.generateMailMessage());
        }
    }
}
