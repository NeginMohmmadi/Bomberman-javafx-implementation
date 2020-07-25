package ir.ac.kntu.menu;

import ir.ac.kntu.GameLoop;
import ir.ac.kntu.gameObject.Player;
import ir.ac.kntu.gameObject.PlayerInfo;
import ir.ac.kntu.save.BinaryPlayerDAO;
import ir.ac.kntu.save.PlayerDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private GridPane root;
    private Scene scene;
    private PlayerDAO playerDAO;
    public Menu(Stage stage,Scene scene,GridPane root){
        playerDAO=new BinaryPlayerDAO();
        root.getChildren().clear();
        Background background = null;
        background = getBackground(new File("src/main/resources/assets/background.png"));
        root.setBackground(background);
        Button newGame=new Button("NEW GAME");
        Button tutorial=new Button("TUTORIAL");
        Button exit=new Button("EXIT");
        root.getStyleClass().add("root");
        newGame.getStyleClass().add("newGame");
        tutorial.getStyleClass().add("tutorial");
        exit.setOnAction(e->{
            stage.close();
        });
        exit.getStyleClass().add("exit");
        this.root=root;
        this.scene=scene;
        newGame.setOnAction(e->{
            setPlayers(stage);
        });
        root.add(newGame,0,0);
        root.add(tutorial,0,1);
        root.add(exit,0,2);
    }

    @NotNull
    private Background getBackground(File file) {
        BackgroundFill backgroundFill= null;
        try {
            backgroundFill = new BackgroundFill(new ImagePattern(new Image
                    (new FileInputStream(file))),
                    CornerRadii.EMPTY, Insets.EMPTY);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new Background(backgroundFill);
    }

    private void setPlayers(Stage stage) {
        Background background = null;
        background = getBackground(new File("src/main/resources/assets/background of game.jpg"));
        root.setBackground(background);
        root.getChildren().clear();
        ArrayList<PlayerInfo> players=playerDAO.getAllPlayers();
        ListView listView=new ListView();
        listView.getItems().addAll(players);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ArrayList<PlayerInfo> result=new ArrayList<>();
        listView.setOnMouseClicked(e->{
            if(result.size()==2) {
                result.remove(result.size() - 1);
            }
            System.out.println("ooo");
            PlayerInfo playerInfo=(PlayerInfo) listView.getSelectionModel().getSelectedItem();
            if(!result.contains(playerInfo)&&playerInfo!=null){
                result.add(playerInfo);
            }
        });
        listView.getStyleClass().add("listView");
        listView.setPrefHeight(400);
        listView.setPrefWidth(500);
        Button submit=new Button("Submit");
        Button add=new Button("AddPlayer");
        add.setOnAction(e->{
            addNewPlayer(listView);
        });
        submit.setOnAction(e->{
            if(result.size()==2) {
                GameLoop gameLoop=new GameLoop(new File("src/main/resources/maps.txt"),stage,scene,root,result);
                gameLoop.startGame();
                root.getChildren().clear();
            }
        });
        root.add(listView,0,0);
        root.add(add,0,1);
        root.add(submit,0,2);
    }

    private void addNewPlayer(ListView listView) {
        Stage stage=new Stage();
        GridPane gridPane=new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        Label label=new Label("Name");
        TextField name=new TextField();
        gridPane.setVgap(20);
        gridPane.setVgap(20);
        Button button=new Button("Save");
        button.setOnAction(e->{
            if(!name.getText().isEmpty()) {
                listView.getItems().add(new PlayerInfo(name.getText()));
                stage.close();
            }
        });
        gridPane.add(label,0,0);
        gridPane.add(name,0,1);
        gridPane.add(button,0,2);
        stage.setTitle("add new player");
        stage.setScene(new Scene(gridPane,300,300,Color.BLUE));
        stage.show();
    }

    public Scene getScene(){
        return scene;
    }
}
