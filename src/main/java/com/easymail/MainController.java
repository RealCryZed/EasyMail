package com.easymail;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

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

    private EntireData entireData = new EntireData();
    private Properties props = new Properties();

    private Logger logger = Logger.getLogger(MainController.class);

    @FXML
    void setToExitButton(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void setSendMailButton(ActionEvent event) {

        entireData.setFrom(from_TextField.getText());
        entireData.setTo(to_TextField.getText());
        entireData.setSubject(subject_TextField.getText());
        entireData.setText(mainTextArea.getText());

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(entireData.getFrom(), "bujhmgtnhjd2002");
            }
                }
                );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(entireData.getFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(entireData.getTo()));
            message.setSubject(entireData.getSubject());
            message.setText(entireData.getText());
            Transport.send(message);

            addLogs();

            System.err.println("Message was sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {


    }

    private void addLogs() {

        Date date = new Date();
        logger.info(date);
        logger.info("FROM: " + entireData.getFrom());
        logger.info("TO: " + entireData.getTo());
        logger.info("SUBJECT: " + entireData.getSubject());
        logger.info("TEXT: " + entireData.getText());
    }
}
