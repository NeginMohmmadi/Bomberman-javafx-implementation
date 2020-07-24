package ir.ac.kntu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Scanner;

public class Test extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("test");
        Pane pane=new Pane();
        Button button=new Button("SALAM");
        pane.getChildren().add(button);
        Scene scene=new Scene(pane,600,600, Color.RED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
