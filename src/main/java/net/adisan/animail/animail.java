package net.adisan.animail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class animail {

    public static void main(String[] args) {
        try {
            animail.sendEmail(
                    "raznar@ausiasmarch.net",
                    "rafaaznar@gmail.com",
                    "xxxxxxxxxxxx password xxxxxxxxxxxxxx",
                    "hola mundo",
                    "Mail de prueba enviado <strong>desde Java</strong>",
                    "html");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void sendEmail(String from, String to, String pass, String emailSubject, String emailText, String mode) throws Exception {
        Properties oProperties = new Properties();
        oProperties.setProperty("mail.smtp.host", "smtp.gmail.com");
        oProperties.setProperty("mail.smtp.starttls.enable", "true");
        oProperties.setProperty("mail.smtp.port", "587");
        oProperties.setProperty("mail.smtp.user", from);
        oProperties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(oProperties);
        try {
            MimeMessage oMimeMessage = new MimeMessage(session);
            oMimeMessage.setFrom(new InternetAddress(from));
            oMimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            oMimeMessage.setSubject(emailSubject);
            oMimeMessage.setText(emailText, "utf-8", mode);
            Transport oTransport = session.getTransport("smtp");
            oTransport.connect(from, pass);
            oTransport.sendMessage(oMimeMessage, oMimeMessage.getAllRecipients());
            oTransport.close();
            System.out.println("Mensaje enviado con éxito");
        } catch (MessagingException oMessagingException) {
            System.out.println("Ha habido un error al enviar el mensaje: " + oMessagingException.getMessage());
        } finally {
            System.out.println("FIN");
        }

    }

}
