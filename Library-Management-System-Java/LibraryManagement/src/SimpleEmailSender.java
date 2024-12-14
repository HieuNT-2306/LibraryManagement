import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class SimpleEmailSender {
    private final String username;
    private final String password;

    public SimpleEmailSender(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void sendEmail(String toAddress, String subject, String messageContent) {
        try {
            Message message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject(subject);
            message.setText(messageContent);

            Transport.send(message);
            System.out.println("Email sent successfully to " + toAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String username = "adamlavie2369@gmail.com"; // Your Gmail address
        String password = "yuot kqws upxn fgip"; // Your Gmail password

        SimpleEmailSender emailSender = new SimpleEmailSender(username, password);
        emailSender.sendEmail("hieubeo22336@gmail.com", "Test Email", "Hello, this is a test email sent from Java!");
    }
}