package SMTP.smtp;

import SMTP.prank.Message;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;

/**
 * @author Florian Conti
 * @author Patrick Furrer
 * Class used to send the mails
 */
public class SmtpClient {

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private final String hostAddress;
    private final int serverPort;

    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;

    /**
     * Constructor of the smtpClient
     * @param hostAddress Address of the SMTP server
     * @param port Port of the SMTP server
     */
    public SmtpClient(String hostAddress, int port) {
        this.hostAddress = hostAddress;
        this.serverPort = port;
    }

    /**
     * Method used to send the mail
     * @param message The message to send
     */
    public void sendMail(Message message) {
        try {
            socket = new Socket(hostAddress, serverPort);

            try {
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            } catch (IOException e) {
                LOG.log(Level.SEVERE, e.toString(), e);
                System.exit(-1);
            }

            // Initiate the SMTP conversation
            out.printf("EHLO localhost\r\n");
            out.flush();
            String line = in.readLine();
            line = in.readLine();

            // Check if smtp error
            if(!line.startsWith("250")){
                throw new IOException("SMTP error: " + line);
            }

            // Get the response of the server
            while(line.startsWith("250-")){
                line = in.readLine();
            }

            // MAIL FROM section
            out.write("MAIL FROM:");
            out.write(message.getFrom());
            out.write("\r\n");
            out.flush();
            line = in.readLine();

            // RCPT TO section
            for(String to : message.getTo()){
                out.write("RCPT TO:");
                out.write(to);
                out.write("\r\n");
                out.flush();
                line = in.readLine();
            }

            // DATA section
            out.write("DATA");
            out.write("\r\n");
            out.flush();
            line = in.readLine();

            out.write("Content-Type: text/plain; charset=utf-8\r\n");

            // Set from in data
            out.write("From: " + message.getFrom() + "\r\n");

            // Set to in date
            out.write("To: " + message.getTo()[0]);
            for(int i = 1; i < message.getTo().length; i++){
                out.write(", " + message.getTo()[i]);
            }
            out.write("\r\n");
            out.flush();

            LOG.info(message.getContent());
            out.write(message.getContent());
            out.write("\r\n");
            out.write(".");
            out.write("\r\n");
            out.flush();
            line = in.readLine();

            // Close the conversation
            out.write("QUIT\r\n");
            out.flush();

        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.toString(), ex);
        } finally {
            try {
                if (out != null) out.close();
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
            try {
                if (in != null) in.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
            try {
                if (socket != null && !socket.isClosed()) socket.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
        }

    }
}
