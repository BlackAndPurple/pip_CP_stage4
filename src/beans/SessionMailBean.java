package beans;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Stateless(name = "SessionMailEJB")
public class SessionMailBean implements IMailBean{
    public SessionMailBean() {
    }

    private final static String FROM = "tanyafromitmo@gmail.com";

    public void send(String newPassword, String to){
        final String username = "tanyafromitmo@gmail.com";//change accordingly
        final String password = "Iloveipad26";//change accordingly

        String host = "smtp.gmail.com"; //smtp

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(FROM));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Sunny Kindergarten password reset");

            // Now set the actual message
            message.setText("Hello! Your new password is: " + newPassword +
                    " \nMake sure to change it on the settings page after signing in!");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
