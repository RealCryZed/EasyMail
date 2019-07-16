package com.easymail;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton mailButton;

    @FXML
    void setMailButton(ActionEvent event) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tank16092002@gmail.com", "bujhmgtnhjd2002");
            }
                }
                );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tank16092002@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("bujhmgtnhjd@bk.ru"));
            message.setSubject("Hi, this is bot's message!");
            message.setText("Hi, I want to be friend with you!");
            Transport.send(message);

            System.out.println("Message was sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {


    }
}
