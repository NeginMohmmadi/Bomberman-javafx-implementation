package ir.ac.kntu.gameObject;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.TimerTask;

public class BombExplosion extends TimerTask {
    private Bomb bomb;
    List<GameObject> gameObjects;

    public BombExplosion(Bomb bomb, List<GameObject> gameObjects){
        this.bomb=bomb;
        this.gameObjects=gameObjects;
    }

    @Override
    public void run() {
        try {
            try {
                bomb.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/explosion/flame2.png")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for(int i=1;i<=bomb.getPower();i++){
                if(bomb.isGoDown()) {
                    Flame flame=new Flame(bomb.getRowIndex()+i,bomb.getColumnIndex(),Direction.DOWN,bomb);
                    bomb.addFlame(flame);
                    gameObjects.add(flame);
                }
                if(bomb.isGoLeft()){
                    Flame flame=new Flame(bomb.getRowIndex(),bomb.getColumnIndex()-i,Direction.LEFT,bomb);
                    bomb.addFlame(flame);
                    gameObjects.add(flame);
                }
                if(bomb.isGoUp()){
                    Flame flame=new Flame(bomb.getRowIndex()-i,bomb.getColumnIndex(),Direction.UP,bomb);
                    bomb.addFlame(flame);
                    gameObjects.add(flame);
                }
                if(bomb.isGoRight()){
                    Flame flame=new Flame(bomb.getRowIndex(),bomb.getColumnIndex()+i,Direction.RIGHT,bomb);
                    bomb.addFlame(flame);
                    gameObjects.add(flame);
                }
                Thread.sleep(200);
                bomb.die();
            }
                    /*gameObjects.add(new Flame(getRowIndex()+1,getColumnIndex(),Direction.DOWN,1));
                    gameObjects.add(new Flame(getRowIndex(),getColumnIndex()-1,Direction.LEFT,1));
                    gameObjects.add(new Flame(getRowIndex(),getColumnIndex()+1,Direction.RIGHT,1));
                    gameObjects.add(new Flame(getRowIndex()-1,getColumnIndex(),Direction.UP,1));
                    Thread.sleep(200);
                    gameObjects.add(new Flame(getRowIndex()+2,getColumnIndex(),Direction.DOWN,2));
                    gameObjects.add(new Flame(getRowIndex(),getColumnIndex()-2,Direction.LEFT,2));
                    gameObjects.add(new Flame(getRowIndex(),getColumnIndex()+2,Direction.RIGHT,2));
                    gameObjects.add(new Flame(getRowIndex()-2,getColumnIndex(),Direction.UP,2));
                    Thread.sleep(200);
                    gameObjects.add(new Flame(getRowIndex()+2,getColumnIndex(),Direction.DOWN,2));
                    gameObjects.add(new Flame(getRowIndex(),getColumnIndex()-2,Direction.LEFT,2));
                    gameObjects.add(new Flame(getRowIndex(),getColumnIndex()+2,Direction.RIGHT,2));
                    gameObjects.add(new Flame(getRowIndex()-2,getColumnIndex(),Direction.UP,2));
                    Thread.sleep(200);
                    gameObjects.add(new Flame(getRowIndex()+3,getColumnIndex(),Direction.DOWN,3));
                    gameObjects.add(new Flame(getRowIndex(),getColumnIndex()-3,Direction.LEFT,3));
                    gameObjects.add(new Flame(getRowIndex(),getColumnIndex()+3,Direction.RIGHT,3));
                    gameObjects.add(new Flame(getRowIndex()-3,getColumnIndex(),Direction.UP,3));
                    Thread.sleep(200);*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
