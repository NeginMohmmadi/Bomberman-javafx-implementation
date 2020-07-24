package ir.ac.kntu;

import ir.ac.kntu.menu.Menu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;


public class JavaFxApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        GridPane root=new GridPane();
        Scene scene = new Scene(root, 800, 600, Color.GREEN);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("root");
        try {
            root.getStylesheets().add(
                    this.getClass()
                            .getResource("/style.css")
                            .toString());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        root.setHgap(0);
        root.setVgap(0);
        Menu menu=new Menu(stage,scene,root);
        stage.setScene(scene);
        stage.setTitle("Fariboorz-bomberman");
        stage.show();
    }
}
