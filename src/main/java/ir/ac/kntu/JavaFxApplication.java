package ir.ac.kntu;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;


public class JavaFxApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        // Setting stage properties
        Director director=new Director(new File("src/main/resources/maps.txt"));
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Faroborz Bobmerman");
        stage.setScene(director.getScene());
        director.startGame();
        stage.show();
    }
}
