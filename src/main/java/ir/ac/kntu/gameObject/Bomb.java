package ir.ac.kntu.gameObject;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Bomb extends GameObject {
    private boolean goUp=true;
    private boolean goDown=true;
    private boolean goRight=true;
    private boolean goLeft=true;
    private int power;
    private ArrayList<Flame> flames;


    public boolean isGoUp() {
        return goUp;
    }

    public boolean isGoDown() {
        return goDown;
    }

    public boolean isGoLeft() {
        return goLeft;
    }

    public boolean isGoRight() {
        return goRight;
    }

    public int getPower() {
        return power;
    }

    public void setPowerUp(int power) {
        this.power=power;
    }

    public void setGoRight(boolean goRight) {
        this.goRight = goRight;
    }

    public void setGoLeft(boolean goLeft) {
        this.goLeft = goLeft;
    }

    public void setGoUp(boolean goUp) {
        this.goUp = goUp;
    }

    public void setGoDown(boolean goDown) {
        this.goDown = goDown;
    }

    public Bomb(int rowIndex, int columnIndex, int power){
        super(rowIndex,columnIndex);
        goDown=true;
        goUp=true;
        goLeft=true;
        goRight=true;
        this.power=power;
        flames=new ArrayList<>();
        try {
            setImage(new Image(new FileInputStream("src/main/resources/assets/map/bombb.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addFlame(Flame flame){
        flames.add(flame);
    }

    public void explosion(List<GameObject> gameObjects){
        Timer timer=new Timer();
        timer.schedule(new BombExplosion(this,gameObjects),3000);
    }

    public void die(){
        super.die();
        for(Flame flame : flames){
            flame.die();
        }
    }
}
