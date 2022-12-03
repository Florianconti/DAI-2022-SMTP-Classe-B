package SMTP.prank;

/**
 * @author Florian Conti
 * @author Patrick Furrer
 * Class used to define a message
 */
public class Message {
    private String from;
    private String[] to = new String[0];
    private String content;

    /**
     * Getter of the mail of the sender
     * @return the mail of the sender
     */
    public String getFrom() {
        return from;
    }

    /**
     * Set the email of the sender
     * @param from the mail of the sender
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Getter of the mails of the victims
     * @return the mails of the victims
     */
    public String[] getTo() {
        return to;
    }

    /**
     * Set the email of the victims
     * @param to Array of the mails
     */
    public void setTo(String[] to) {
        this.to = to;
    }

    /**
     * Getter of the content of the mail
     * @return the content of the mail
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the content of the message
     * @param content message to send
     */
    public void setContent(String content) {
        this.content = content;
    }
}
