package ir.ac.kntu.menu;

import ir.ac.kntu.GameLoop;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Menu {
    private GridPane root;
    private Scene scene;
    public Menu(Scene scene,GridPane root){
        BackgroundFill backgroundFill= null;
        try {
            backgroundFill = new BackgroundFill(new ImagePattern(new Image
                    (new FileInputStream("src/main/resources/assets/background.png"))),
                    CornerRadii.EMPTY, Insets.EMPTY);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Background background=new Background(backgroundFill);
        root.setBackground(background);
        this.root=root;
        this.scene=scene;
        Button newGame=new Button("NEW GAME");
        Button tutorial=new Button("TUTORIAL");
        Button exit=new Button("EXIT");
        root.getStyleClass().add("root");
        newGame.getStyleClass().add("newGame");
        tutorial.getStyleClass().add("tutorial");
        exit.getStyleClass().add("exit");
        newGame.setOnAction(e->{
            GameLoop gameLoop=new GameLoop(new File("src/main/resources/maps.txt"),scene,root);
            gameLoop.startGame();
        });
        root.add(newGame,0,0);
        root.add(tutorial,0,1);
        root.add(exit,0,2);
    }

    public Scene getScene(){
        return scene;
    }
}
