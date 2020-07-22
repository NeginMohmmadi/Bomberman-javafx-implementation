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
        /*GridPane root=new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-border-width: 0 0 5 0;");
        root.setHgap(1);
        root.setVgap(1);
        Scene scene = new Scene(root, 800, 600, Color.GREEN);
        MapParser mapParser=new MapParser();
        ArrayList<ArrayList<GameObject>> gameObjects=mapParser.gameObjects(new File("src/main/resources/maps.txt"));
        KeyLogger keyLogger=new KeyLogger(scene);
        for(ArrayList<GameObject> rowObjects : gameObjects){
            for (GameObject gameObject : rowObjects){
                if(gameObject instanceof KeyListener){
                    keyLogger.addListener((KeyListener)gameObject);
                }
                ImageView imageView=new ImageView();
                imageView.setImage(gameObject.getImage());
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                root.add(imageView,gameObject.getColumnIndex(),gameObject.getRowIndex());
            }
        }
        AnimationTimer animationTimer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                root.getChildren().clear();
                for(ArrayList<GameObject> rowObjects : gameObjects){
                    for (GameObject gameObject : rowObjects){
                        ImageView imageView=new ImageView();
                        imageView.setImage(gameObject.getImage());
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);
                        root.add(imageView,gameObject.getColumnIndex(),gameObject.getRowIndex());
                    }
                }
            }
        };
        animationTimer.start();
        //Player player=new Player(1,0,0);
        ImageView imageView=new ImageView();
        FileInputStream file=new FileInputStream("src/main/resources/assets/player/player_down_standing.png");
        Image image=new Image(file);
        Image image2=new Image(new FileInputStream("F:\\javaProject\\hw5-fariborz-bomberman-starter\\src\\main\\java\\ir\\ac\\kntu\\player_down_standing.png"));
        Image image3=new Image(file);
        imageView.setImage(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(30);
        imageView.setX(0);
        imageView.setY(50);
        root.add(imageView,0,0);
        ImageView imageView1=new ImageView();
        imageView1.setImage(image2);
        root.add(imageView1,1,0);
        //root.add(new Ball(1,10,10),2,0);
        root.getChildren().remove(ball);
        root.add(ball,0,0);
        scene.addEventHandler(KeyEvent.KEY_PRESSED,key ->{
            if(key.getCode()==KeyCode.UP){
                ball.goUp(root);
            }else if(key.getCode()==KeyCode.DOWN){
                ball.goDown(root);
            }else if(key.getCode()==KeyCode.RIGHT){
                ball.goRight(root);
            }else if(key.getCode()==KeyCode.LEFT){
                ball.goLeft(root);
            }
        });*/
        // Setting stage properties
        Director director=new Director(new File("src/main/resources/maps.txt"));
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Faroborz Bobmerman");
        stage.setScene(director.getScene());
        director.startGame();
        stage.show();
    }
}
