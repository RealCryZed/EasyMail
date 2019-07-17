module EasyMail {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.mail;
    requires log4j;

    opens com.easymail to javafx.fxml;
    exports com.easymail;
}