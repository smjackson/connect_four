package com.adharatech.connect_four;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller = (Controller)loader.getController();

        controller.init(primaryStage);

        primaryStage.setTitle("Connect Four");
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
