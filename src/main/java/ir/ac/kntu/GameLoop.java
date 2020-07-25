package ir.ac.kntu;

import ir.ac.kntu.gameObject.Bomb;
import ir.ac.kntu.gameObject.GameObject;
import ir.ac.kntu.gameObject.Player;
import ir.ac.kntu.gameObject.PlayerInfo;
import ir.ac.kntu.keyboard.KeyListener;
import ir.ac.kntu.keyboard.KeyLogger;
import ir.ac.kntu.map.MapParser;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.save.BinaryPlayerDAO;
import ir.ac.kntu.save.PlayerDAO;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;
public class GameLoop {
    private Stage stage;
    private GridPane root;
    private Scene scene;
    private MapParser mapParser;
    private KeyLogger keyLogger;
    private AnimationTimer animationTimer;
    private List<GameObject> gameObjects;
    private AddRandomObject addRandomObject;
    private final int gameTime=185000;
    private int numOfPlayers;
    private int deltaTime;
    private long startTime;
    private boolean end;
    private ArrayList<PlayerInfo> players;

    public GameLoop(File file, Stage stage,Scene scene, GridPane root, ArrayList<PlayerInfo> players){
        init(scene, root);
        this.stage=stage;
        this.players=players;
        numOfPlayers=players.size();
        mapParser=new MapParser(players);
        gameObjects=mapParser.gameObjects(file);
        setAnimationTimer();
        keyLogger=new KeyLogger(scene,gameObjects);
        addRandomObject=new AddRandomObject(gameObjects);
    }

    private void setAnimationTimer() {
        animationTimer=new AnimationTimer() {
            private int count=0;
            @Override
            public void handle(long now) {
                if(count<5){
                    root.getChildren().clear();
                    count++;
                    TextField textField=new TextField((String.valueOf(count)));
                    textField.setId("textField");
                    root.add(textField,0,0);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                if(end){
                    endGame();
                    return;
                }
                deltaTime=(int)(new Date().getTime()-startTime)/1000;
                checkCollide();
                clean();
                if (numOfPlayers<=1){
                    end=true;
                }
                addObjectsToRoot();
            }
        };
    }

    private void init(Scene scene, GridPane root) {
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-border-width: 0 0 5 0;");
        root.setHgap(0);
        root.setVgap(0);
        BackgroundFill backgroundFill=new BackgroundFill(Color.GRAY,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background=new Background(backgroundFill);
        root.setBackground(background);
        this.root=root;
        this.scene=scene;
    }

    private void clean() {
        for (int i=0;i<gameObjects.size();i++){
            if(!gameObjects.get(i).isAlive()){
                if(gameObjects.get(i) instanceof Player) {
                    keyLogger.removeListener(gameObjects.get(i));
                    ((Player) gameObjects.get(i)).setDeadTime(deltaTime);
                    numOfPlayers--;
                }
                gameObjects.remove(i);
            }
        }
    }

    private void checkCollide(){
        for (int i=0;i<gameObjects.size();i++){
            for(int j=i+1;j<gameObjects.size();j++){
                if(gameObjects.get(i).isColliding(gameObjects.get(j))){
                    gameObjects.get(i).collide(gameObjects.get(j));
                    gameObjects.get(j).collide(gameObjects.get(i));
                }
            }
        }
    }
    public void addObjectsToRoot() {
        root.getChildren().clear();
        Text text=new Text();
        int time=(gameTime/1000-deltaTime);
        int minute=time/60;
        int seconds=time%60;
        text.setText("TIME    "+minute+":"+seconds);
        text.getStyleClass().add("text");
        text.setId("text");
        root.add(text,0,0,5,1);
        for (int i=0;i<gameObjects.size();i++){
            if(gameObjects.get(i) instanceof KeyListener){
                keyLogger.addListener((KeyListener)gameObjects.get(i));
            }
            if(!gameObjects.get(i).isVisible()){
                continue;
            }
            ImageView imageView=new ImageView();
            imageView.setImage(gameObjects.get(i).getImage());
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            root.add(imageView,gameObjects.get(i).getColumnIndex(),gameObjects.get(i).getRowIndex()+1);
        }
    }

    public void startGame(){
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                end=true;
            }
        },gameTime);
        animationTimer.start();
        startTime=new Date().getTime();
        addRandomObject.go();
    }

    public void endGame(){
        animationTimer.stop();
        keyLogger.removeAllListeners();
        addRandomObject.stop();
        ArrayList<Player> players=removeBombsAndGetPlayers();
        if(players.size()==1){
            players.get(0).win(3);
        }else {
            for (Player player : players) {
                if (player.isAlive()) {
                    player.win(1);
                }
            }
        }
        PlayerDAO playerDAO=new BinaryPlayerDAO();
        playerDAO.saveAllPlayers(this.players);
        showRanks();
    }

    private ArrayList<Player> removeBombsAndGetPlayers() {
        ArrayList<Player> players=new ArrayList<>();
        for(int i=0;i<gameObjects.size();i++){
            if(gameObjects.get(i) instanceof Player){
                players.add((Player)gameObjects.get(i));
            }
            if(gameObjects.get(i) instanceof Bomb){
                gameObjects.remove(i);
            }
        }
        return players;
    }

    private void showRanks() {
        root.getChildren().clear();
        Collections.sort(players);
        ListView listView=new ListView();
        listView.setPrefWidth(500);
        listView.setPrefHeight(600);
        listView.getStyleClass().add("listView");
        int i=0;
        for (PlayerInfo player : players){
            i++;
            listView.getItems().add(i+". "+player.getName()+"             "+player.getLastScore());
        }
        root.add(listView,0,0);
        Button button=new Button("BACK");
        button.setOnAction(e->{
            Menu menu=new Menu(stage,scene,root);
        });
        root.add(button,1,0);
    }

    public Scene getScene(){
        return this.scene;
    }
}
