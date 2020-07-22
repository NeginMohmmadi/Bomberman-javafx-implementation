package ir.ac.kntu;

import ir.ac.kntu.gameObject.GameObject;
import ir.ac.kntu.keyboard.KeyListener;
import ir.ac.kntu.keyboard.KeyLogger;
import ir.ac.kntu.map.MapParser;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Director {
    private GridPane root;
    private Scene scene;
    private MapParser mapParser;
    private KeyLogger keyLogger;
    private AnimationTimer animationTimer;
    private List<GameObject> gameObjects;
    private AddRandomObject addRandomObject;

    public Director(File file){
        root=new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-border-width: 0 0 5 0;");
        root.setHgap(1);
        root.setVgap(1);

        scene = new Scene(root, 800, 600,Color.rgb(0,123,48));
        mapParser=new MapParser();
        gameObjects=mapParser.gameObjects(file);
        animationTimer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                checkCollide();
                clean();
                addObjectsToRoot();
            }
        };
        keyLogger=new KeyLogger(scene,gameObjects);
        addRandomObject=new AddRandomObject(gameObjects);
    }

    private void clean() {
       for (int i=0;i<gameObjects.size();i++){
           if(!gameObjects.get(i).isAlive()){
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
            root.add(imageView,gameObjects.get(i).getColumnIndex(),gameObjects.get(i).getRowIndex());
        }
    }

    public void addGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
    }

    public void startGame(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(addRandomObject);
        executorService.shutdown();
        animationTimer.start();
    }

    public Scene getScene(){
        return this.scene;
    }
}
