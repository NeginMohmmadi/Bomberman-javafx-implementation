package ir.ac.kntu.animation;

import ir.ac.kntu.gameObject.*;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.TimerTask;

public class BombExplosion extends TimerTask {
    private Bomb bomb;
    private boolean changeUp;
    private boolean changeDown;
    private boolean changeLeft;
    private boolean changeRight;
    private int upBound;
    private int downBound;
    private int leftBound;
    private int rightBound;
    List<GameObject> gameObjects;

    public BombExplosion(Bomb bomb, List<GameObject> gameObjects){
        this.bomb=bomb;
        this.upBound=bomb.getPower();
        this.downBound=bomb.getPower();
        this.leftBound=bomb.getPower();
        this.rightBound=bomb.getPower();
        this.gameObjects=gameObjects;
    }

    private boolean checkCollideWithWall(int rowIndex,int columnIndex){
        for(int i=0;i<gameObjects.size();i++){
            if(gameObjects.get(i).isColliding(rowIndex,columnIndex)){
                if((gameObjects.get(i) instanceof Wall)||(gameObjects.get(i) instanceof Block)){
                    return true;
                }
            }
        }
        return false;
    }

    private void setBounds(){
        setDownBound();
        setUpBound();
        setRightBound();
        setLeftBound();
        //System.out.println(upBound+" "+downBound+" "+rightBound+" "+leftBound);
    }

    private void setLeftBound() {
        for (int i=1;i<=bomb.getPower();i++){
            if(checkCollideWithWall(bomb.getRowIndex(),bomb.getColumnIndex()-i)){
                bomb.setGoLeft(false);
                leftBound=i;
                changeLeft=true;
                break;
            }
        }
    }

    private void setRightBound() {
        for (int i=1;i<=bomb.getPower();i++){
            if(checkCollideWithWall(bomb.getRowIndex(),bomb.getColumnIndex()+i)){
                bomb.setGoRight(false);
                rightBound=i;
                changeRight=true;
                break;
            }
        }
    }

    private void setUpBound() {
        for (int i=1;i<=bomb.getPower();i++){
            if(checkCollideWithWall(bomb.getRowIndex()-i,bomb.getColumnIndex())){
                bomb.setGoUp(false);
                upBound=i;
                changeUp=true;
                break;
            }
        }
    }

    private void setDownBound() {
        for (int i=1;i<=bomb.getPower();i++){
            if(checkCollideWithWall(bomb.getRowIndex()+i,bomb.getColumnIndex())){
                bomb.setGoDown(false);
                downBound=i;
                changeDown=true;
                break;
            }
        }
    }

    @Override
    public void run() {
        try {
            bomb.setActive(true);
            setBounds();
            for(int j=1;j<=4;j++) {
                bomb.setImage(j);
                downExplosion(j);
                upExplosion(j);
                rightExplosion(j);
                leftExplosion(j);
                Thread.sleep(60);
            }
            for(int j=4;j>=1;j--) {
                bomb.setImage(j);
                downExplosion(j);
                upExplosion(j);
                rightExplosion(j);
                leftExplosion(j);
                Thread.sleep(60);
            }
            bomb.die();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void leftExplosion(int j) {
        for (int i = 1; i <= leftBound; i++) {
            Flame flame = new Flame(bomb.getRowIndex(),bomb.getColumnIndex()-i,
                    Direction.LEFT,bomb.getPower(),i,j);
            bomb.addFlame(flame);
            gameObjects.add(flame);
            if(i==leftBound&&changeLeft){
                flame.setVisible(false);
            }
        }
    }

    private void rightExplosion(int j) {
        for (int i = 1; i <= rightBound; i++) {
            Flame flame = new Flame(bomb.getRowIndex(),bomb.getColumnIndex()+i,
                    Direction.RIGHT,bomb.getPower(),i,j);
            bomb.addFlame(flame);
            gameObjects.add(flame);
            if(i==rightBound&&changeRight){
                flame.setVisible(false);
            }
        }
    }

    private void upExplosion(int j) {
        for (int i = 1; i <= upBound; i++) {
            Flame flame = new Flame(bomb.getRowIndex() - i,
                    bomb.getColumnIndex(), Direction.UP,bomb.getPower(),i,j);
            bomb.addFlame(flame);
            gameObjects.add(flame);
            if(i==upBound&&changeUp){
                flame.setVisible(false);
            }
        }
    }

    private void downExplosion(int j) {
        for (int i = 1; i <= downBound; i++) {
            Flame flame = new Flame(bomb.getRowIndex() + i,
                    bomb.getColumnIndex(), Direction.DOWN,bomb.getPower(),i,j);
            bomb.addFlame(flame);
            gameObjects.add(flame);
            if(i==downBound&&changeDown){
                flame.setVisible(false);
            }
        }
    }
}
