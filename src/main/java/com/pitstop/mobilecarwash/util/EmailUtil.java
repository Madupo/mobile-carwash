package com.pitstop.mobilecarwash.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Emmie on 2017/05/05.
 */
public class EmailUtil {


    public static void sendEmail(String recipient,String subject, String imageUrl,String loginUrl){

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
                String msg =  "Thank you for creating an account." +  "\n\nPlease go to " + loginUrl + " to sign in";

                message.setText(msg);

                Transport.send(message);

                System.out.println("Done sending emails");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
            /*// Sender's email ID needs to be mentioned
            String from = "emmanuelmadupo@gmail.com";

            // Assuming you are sending email from localhost
            String host = "46.101.253.144";

            // Get system properties
            Properties properties = System.getProperties();

            // Setup mail server
            properties.setProperty("mail.smtp.host", host.trim());

            // Get the default Session object.
            Session session = Session.getDefaultInstance(properties);

            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            String msg =  "Thank you for creating an account." +  "\n\nPlease go to " + loginUrl + " to sign in";
            message.setText(msg);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
            System.out.println("error in emails");
        }*/





}




