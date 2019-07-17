package com.easymail;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton sendMailButton;

    @FXML
    private TextArea mainTextArea;

    @FXML
    private TextField from_TextField;

    @FXML
    private TextField to_TextField;

    @FXML
    private TextField subject_TextField;

    @FXML
    private JFXButton exitButton;

    private Properties props = new Properties();

    @FXML
    void setToExitButton(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void setSendMailButton(ActionEvent event) {

        String from = from_TextField.getText();
        String to = to_TextField.getText();
        String subject = subject_TextField.getText();
        String text = mainTextArea.getText();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "bujhmgtnhjd2002");
            }
                }
                );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);

            System.err.println("Message was sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {


    }

    private void sendEmailMessage() {

    }
}
