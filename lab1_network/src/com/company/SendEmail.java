package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

public class SendEmail {
    public static void main(String[] args) throws IOException {
        // Recipient's email ID needs to be mentioned.
//        FileInputStream fileInputStream = new FileInputStream("config.property");
//        String to = "barca57@mail.ru";
//        Properties properties = new Properties();
//        properties.load(fileInputStream);
//        String user = properties.getProperty("mail.user");
//        String password = properties.getProperty("mail.password");
//        String host = properties.getProperty("mail.host");
//
//        // Sender's email ID needs to be mentioned
//        //  String from = "fromemail@gmail.com";
//        //final String username = "manishaspatil";//change accordingly
//        //final String password = "******";//change accordingly
//
//        // Assuming you are sending email through relay.jangosmtp.net
//        String hostSMTP = "smtp.yandex.host";
//        Integer port = 465;
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", port);
//
//        // Get the Session object.
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(user, password);
//                    }
//                });
//
//        try {
//            // Create a default MimeMessage object.
//            Message message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(user));
//            InternetAddress[] addresses = {new InternetAddress(to)};
//            // Set To: header field of the header.
//            message.setRecipients(Message.RecipientType.TO,
//                    addresses);
//
//            // Set Subject: header field
//            message.setSubject("Testing Subject");
//            message.setSentDate(new Date());
////            // Now set the actual message
//            message.setText("tryasy OGROMNOY ass ");
////
////            // Send message
//            Transport.send(message);
////
//            System.out.println("Sent message successfully....");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//
//        }
    }
}
