package com.pitstop.mobilecarwash.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Emmie on 2017/05/05.
 */
public class EmailUtil {


    public static void sendEmail(String recipient,String subject, String imageUrl){

        try { final String username = "emmanuelmadupo@gmail.com";
            final String password = "Emmie@24";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });


                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(recipient));
                message.setSubject(subject);
                String msg =  "Welcome to Pitstop Cleaning :)" +"\n" + "We hope you enjoy our ultimate car grooming service." +"\n"
                            + " What are you waiting for? Lets book your first wash! " + "\n" + "<b>Pitstop Cleaning Team</b>" + "\n" +
                            "AFFORDABLE. GREEN. MOBILE";

                message.setText(msg);

                Transport.send(message);

                System.out.println("Done sending emails");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }


    public static void sendContactUsEmail(String name,String sender,String contactNumber, String emailMessage) throws MessagingException{

        final String username = "emmanuelmadupo@gmail.com";
            final String password = "Emmie@24";

        System.out.println("user to send email is " + sender);
        System.out.println("user to receive email is " + username);

        String senderId=sender;
        String receiverId = username;

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

        String emailText= "Sender Email:  %s." +"\n" + "Sender Contact number:  %s" +"\n\n"+ " Message: " +"\n" + "%s";
        emailText = String.format(emailText,senderId, contactNumber, emailMessage);
        System.out.println("**Formatted sms text: "+emailText);



            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderId));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiverId));
            message.setSubject("Contact Us Enquiry");

            message.setText(emailText);

            Transport.send(message);

            System.out.println("Done sending contact us emails");


    }







}




