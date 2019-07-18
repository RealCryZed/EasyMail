package com.easymail;

import com.jfoenix.controls.JFXButton;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;

import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;

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
    private TextField to_TextField;

    @FXML
    private TextField subject_TextField;

    @FXML
    private TextField fileName;

    @FXML
    private TextField filePath;

    @FXML
    private TextField mailToSignIn;

    @FXML
    private PasswordField passwordToSignIn;

    @FXML
    private JFXButton exitButton;

    @FXML
    private JFXButton attachButton;

    private EntireData data = new EntireData();
    private Properties props = new Properties();

    private final static Logger logger = Logger.getLogger(MainController.class.getName());

    @FXML
    void setToExitButton(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void setAttachButton(ActionEvent event) {

        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        data.setAttachment_path(f.getAbsolutePath());
        data.setAttachment_name(f.getName());
        filePath.setText(data.getAttachment_path());
        fileName.setText(data.getAttachment_name());
    }

    @FXML
    void setSendMailButton(ActionEvent event) {

        data.setFrom(mailToSignIn.getText());
        data.setTo(to_TextField.getText());
        data.setSubject(subject_TextField.getText());
        data.setText(mainTextArea.getText());

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailToSignIn.getText(), passwordToSignIn.getText());
            }
                }
                );

        try {
            Message message = new MimeMessage(session);
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            message.setFrom(new InternetAddress(data.getFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(data.getTo()));
            message.setSubject(data.getSubject());
            messageBodyPart.setText(data.getText());

            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(data.getAttachment_path());
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(data.getAttachment_name());
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

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
        logger.info(date.toString());
        logger.info("FROM: " + data.getFrom());
        logger.info("TO: " + data.getTo());
        logger.info("ATTACHMENT: " + data.getAttachment_name());
        logger.info("SUBJECT: " + data.getSubject());
        logger.info("TEXT: " + data.getText());
        logger.info("--------------------------------------------------");
    }
}
