package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Pouria on 7/21/2019.
 * Project Name winSevenCalculator
 */
public class Main extends Application {

    public static void main(String[] args) {
        Main.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(
                new Scene(
                        (Parent) FXMLLoader.load(getClass().getResource("/view/calculatorView.fxml"))
                        , 334, 450));

        primaryStage.show();

    }

}

