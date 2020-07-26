package ir.ac.kntu.menu;

import ir.ac.kntu.GameLoop;
import ir.ac.kntu.gameObject.PlayerInfo;
import ir.ac.kntu.save.BinaryPlayerDAO;
import ir.ac.kntu.save.PlayerDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private GridPane root;
    private Scene scene;
    private PlayerDAO playerDAO;
    private Map<File,ImageView> maps;
    public Menu(Stage stage,Scene scene,GridPane root){
        maps=new HashMap<>();
        playerDAO=new BinaryPlayerDAO();
        drawMenu(stage,scene,root);
    }

    private void drawMenu(Stage stage, Scene scene, GridPane root) {
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
        editButtons(stage, scene, root, newGame, tutorial, exit);
        root.add(newGame,0,0);
        root.add(tutorial,0,1);
        root.add(exit,0,2);
    }

    private void editButtons(Stage stage, Scene scene, GridPane root, Button newGame, Button tutorial, Button exit) {
        tutorial.setOnAction(e->{
            try {
                root.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY)));
                ImageView imageView=new ImageView(new Image(new FileInputStream("src/main/" +
                        "resources/assets/keyboard-r1-6.gif")));
                imageView.setFitWidth(500);
                imageView.setFitHeight(300);
                Label player1=new Label("Player1");
                Label player2=new Label("Player2");
                player1.setId("player1");
                player2.setId("player2");
                Button button=new Button("Back");
                button.setOnAction(ev->{
                    drawMenu(stage,scene,root);
                });
                root.getChildren().clear();
                root.add(player1,0,0);
                root.add(player2,0,1);
                root.add(imageView,0,2,2,1);
                root.add(button,0,3);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        exit.setOnAction(e->{
            stage.close();
        });
        exit.getStyleClass().add("exit");
        this.root=root;
        this.scene=scene;
        newGame.setOnAction(e->{
            setPlayers(stage);
        });
    }

    @NotNull
    private Background getBackground(File file) {
        BackgroundFill backgroundFill= null;
        try {
            backgroundFill = new BackgroundFill(new ImagePattern(
                    new Image(new FileInputStream(file))),CornerRadii.EMPTY, Insets.EMPTY);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new Background(backgroundFill);
    }

    private void setPlayers(Stage stage) {
        Background background=new Background(new BackgroundFill(Color.rgb(120,144,255),
                CornerRadii.EMPTY,Insets.EMPTY));
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
                GameLoop gameLoop=new GameLoop(new File("src/main/resources/map/map1.txt")
                        ,stage,scene,root,result);
                gameLoop.startGame();
                root.getChildren().clear();
            }
        });
        root.add(listView,0,0);
        root.add(add,0,1);
        root.add(submit,0,2);
    }

    /*private void setMap(Stage stage,ArrayList<PlayerInfo> result) {
        ImageView map1= null;
        ImageView map2=null;
        try {
            map1 = new ImageView(new Image(new FileInputStream("images/map1.png")));
            map2=new ImageView(new Image(new FileInputStream("images/map2.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //maps.put(new File("src/main/resources/map1.txt"),map1);
        //maps.put(new File("src/main/resources/map2.txt"),map2);
        File selected=null;
        map1.setOnMouseClicked(e->{
            selected=maps.get(map1);
        });
        Button submit=new Button("submit");
        submit.setOnAction(e->{
            GameLoop gameLoop=new GameLoop(new File
                    ("src/main/resources/map1.txt"),stage,scene,root,result);
            gameLoop.startGame();
            root.getChildren().clear();
        });

    }*/

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
