package ir.ac.kntu;

import ir.ac.kntu.gameObject.GameObject;
import ir.ac.kntu.gameObject.Player;
import ir.ac.kntu.keyboard.KeyListener;
import ir.ac.kntu.keyboard.KeyLogger;
import ir.ac.kntu.map.MapParser;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameLoop {
    private GridPane root;
    private Scene scene;
    private MapParser mapParser;
    private KeyLogger keyLogger;
    private AnimationTimer animationTimer;
    private List<GameObject> gameObjects;
    private AddRandomObject addRandomObject;
    private final int GAME_TiME=180000;
    private int deltaTime;
    private long startTime;
    private boolean end;
    private ArrayList<Player> players;

    public GameLoop(File file,Scene scene,GridPane root,ArrayList<Player> players){
        init(scene, root);
        this.players=players;
        mapParser=new MapParser(players);
        gameObjects=mapParser.gameObjects(file);
        setAnimationTimer();
        keyLogger=new KeyLogger(scene,gameObjects);
        addRandomObject=new AddRandomObject(gameObjects);
    }

    private void setAnimationTimer() {
        animationTimer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(!end) {
                    deltaTime=(int)(new Date().getTime()-startTime)/1000;
                    System.out.println(deltaTime);
                    checkCollide();
                    clean();
                    if (players.size()==0){
                        end=true;
                    }
                    addObjectsToRoot();
                }
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
               keyLogger.removeListener(gameObjects.get(i));
               players.remove(gameObjects.get(i));
               gameObjects.remove(i);
           }else {
               if(gameObjects.get(i) instanceof Player&&!players.contains(gameObjects.get(i))){
                   players.add((Player)gameObjects.get(i));
               }
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
        int time=(GAME_TiME/1000-deltaTime);
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
        },GAME_TiME);
        animationTimer.start();
        startTime=new Date().getTime();
        addRandomObject.go();
    }

    public void endGame(){
        animationTimer.stop();

    }

    public Scene getScene(){
        return this.scene;
    }
}
