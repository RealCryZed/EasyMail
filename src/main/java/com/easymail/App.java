package com.easymail;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load((getClass().getResource("com.easymail/mainPage.fxml")));
        Scene scene = new Scene(root);

//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}
