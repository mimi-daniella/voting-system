package com.bascode.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailUtil {
    public static void sendVerificationEmail(String to, String otp) throws MessagingException {
        String host = "smtp.gmail.com";
        String from = System.getenv("EMAIL_FROM");         
        final String username = System.getenv("EMAIL_USER"); 
        final String password = System.getenv("EMAIL_PASS"); 
        String subject = "Your OTP Code";
        String content = "Your OTP code for email verification is: " + otp +
                "\n\nEnter this code on the verification page to activate your account.";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }
    
    
    //forgot password
    // Send password reset email
    public static void sendPasswordResetEmail(String to, String resetLink) throws MessagingException {
        final String username = System.getProperty("EMAIL_USER");
        final String password = System.getProperty("EMAIL_PASSWORD");

        String subject = "Password Reset Link";
        String content = "You requested a password reset.\n\n" +
                "Reset your password using this link:\n" + resetLink + "\n\n" +
                "If you did not request this, you can ignore this email.";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }

    public static void sendPasswordResetEmail1(String to, String resetLink) throws MessagingException {
        String host = "smtp.gmail.com";
        final String username = System.getProperty("EMAIL_USER"); 
        final String password = System.getProperty("EMAIL_PASSWORD");
        String from = System.getProperty("EMAIL_USER"); 


        String subject = "Password Reset Request";
        String content = "We received a request to reset your password.\n\n"
                + "Use the link below to set a new password:\n"
                + resetLink + "\n\n"
                + "If you didn't request this, you can ignore this email.";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }
}
