package lk.ijse.semisterfinal.Mail;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.semisterfinal.Tm.SalaryTm;
import lombok.Getter;
import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

public class Mail implements Runnable{

    @Setter
    @Getter
    private String Msg;
    @Setter
    @Getter
    private String To;
    @Setter
    @Getter
    private String Subject;

    public static void outMail(String msg, String to, String subject) throws MessagingException {

        String from = "nshanilka999@gmail.com";
        String host = "localhost";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("nshanilka999@gmail.com", "wupawoazypqsfghh");
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(from));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(msg);
        Transport.send(mimeMessage);

        System.out.println("Sent... " + to);
    }

    public static void outMail(String msg, ArrayList<String> to, String subject) throws MessagingException {
        for (String ele : to) {
            outMail(msg, ele, subject);
        }
    }

    @Override
        public void run() {
            if (Msg != null) {
                try {
                    outMail(Msg,To,Subject);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("not sent. empty msg!");
            }
        }

}
