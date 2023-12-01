package lk.ijse.semisterfinal.controller;

import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMailUtil {
    public static void sendMail(String recepient, int otp) throws MessagingException {
        try {
            System.out.println("Preparing to send email");
            Properties properties = new Properties();


            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            String myAccountEmail = "nshanilka999@gmail.com";
            String password = "wupawoazypqsfghh";

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, password);
                }
            });
            Message message = prepareMessage(session, myAccountEmail, recepient, otp);
            Transport.send(message);
            System.out.println("Email Send successfully");
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Connect Internet Connection !!").show();
        }
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, int otp) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Your OTP");
            message.setText("Your OTP is");
            return message;
        } catch (Exception e) {
//            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Connect Internet Connection !!").show();
        }
        return null;
    }

}
