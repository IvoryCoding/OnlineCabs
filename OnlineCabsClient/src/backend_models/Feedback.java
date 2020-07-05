package backend_models;

import frontend_viewcontroller.LoadingViewDisplay;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;

public class Feedback {
    LoadingViewDisplay theLoadingViewDisplay;

    public static void mail(String from, String subject, String message) {
        String username = "camerongillespie15";
        String password = "zcxvbmn,";
        String recipiant = "camerongillespie15@gmail.com";
        String[] to = {recipiant};
        
        sendFromGmail(username, password, to, subject, message);
    }
    
    private static void sendFromGmail(String from, String pass, String[] to, String subject, String message) {
        
        
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(props);
        MimeMessage mimeMessage = new MimeMessage(session);
        
        try {
            mimeMessage.setFrom(from);
            InternetAddress[] toAddress = new InternetAddress[to.length];
            
            for(int i = 0; i < toAddress.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            
            for (int i = 0; i < toAddress.length; i++) {
                mimeMessage.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        }catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}
