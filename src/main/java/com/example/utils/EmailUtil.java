package com.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUtil {
    
    // Method to load email properties from the properties file
    private static Properties loadMailProperties() {
        Properties properties = new Properties();
        try (InputStream input = EmailUtil.class.getClassLoader().getResourceAsStream("mail.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find mail.properties");
                return null;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    // Method to send email
    public static void sendEmail(String recipient, String subject, String content) {
        // Load properties from mail.properties
        Properties props = loadMailProperties();
        if (props == null) {
            System.out.println("Failed to load mail properties.");
            return;
        }

        // Sender email and password will be fetched from the properties
        final String senderEmail = props.getProperty("mail.smtp.username");
        final String senderPassword = props.getProperty("mail.smtp.password");
        
        // Set up the session with the email server
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(content);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully to " + recipient);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email.");
        }
    }
}
