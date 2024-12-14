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

    public void sendEmail(String toAddress, String username, String bookTitle, String author, String dueDate) {
        try {
            Message message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject("Library Book Information");

            String messageContent = generateEmailContent(username, bookTitle, author, dueDate);
            message.setContent(messageContent, "text/html");

            Transport.send(message);
            System.out.println("Email sent successfully to " + toAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateEmailContent(String username,String bookTitle, String author, String dueDate) {
        return "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<h2 style='color: #9d1d1f;'>Library Book Information:</h2>" +
                "<p style='color: #333;'>Dear " + username+", </p>" +
                "<p style='color: #333;'>Here are the details of your borrowed book:</p>" +
                "<table style='border-collapse: collapse; width: 100%;'>" +
                "<tr style='background-color: #efb60b;'>" +
                "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px; color: #fff;'>Book Title</th>" +
                "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px; color: #fff;'>Author</th>" +
                "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px; color: #fff;'>Due Date</th>" +
                "</tr>" +
                "<tr>" +
                "<td style='border: 1px solid #dddddd; padding: 8px;'>" + bookTitle + "</td>" +
                "<td style='border: 1px solid #dddddd; padding: 8px;'>" + author + "</td>" +
                "<td style='border: 1px solid #dddddd; padding: 8px;'>" + dueDate + "</td>" +
                "</tr>" +
                "</table>" +
                "<p style='color: #333; font-weight: bold;'>Remember to return the book before due date, or you might be banned from using our services!</p>" +
                "<p style='color: #333;'>Thank you for using our library system!</p>" +
                "<p style='color: #9d1d1f; font-style: italic;'>Best regards,<br>Library  Manager - Hust</p>" +
                "</body>" +
                "</html>";
    }

    public static void main(String[] args) {
        String username = "adamlavie2369@gmail.com"; 
        String password = ""; 

        SimpleEmailSender emailSender = new SimpleEmailSender(username, password);
        emailSender.sendEmail("hieubeo2369@gmail.com", "hieu", "Brave New World", "Aldous Huxley", "18-12-2024");
    }
}