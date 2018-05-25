package ch.supsi.gui;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;


public class PtmMain extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        URL resource = getClass().getResource("/Scene.fxml");
        // blablaCar = new URL("https://public-api.blablacar.com/api/v2/trips?key=f762695b281c4672abd2df1c40878afb&fn=Paris&tn=London&locale=en_GB&_format=json");
        //BufferedReader reader = new BufferedReader(new InputStreamReader(blablaCar.openStream()));
        //String s;
        // while ((s = reader.readLine()) != null)
        // System.out.println(s);

        Parent root = FXMLLoader.load(resource);

        Scene scene = new Scene(root);
        //scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("TBC");
        stage.setScene(scene);
        stage.setMinHeight(600);
        stage.setMinWidth(1000);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}