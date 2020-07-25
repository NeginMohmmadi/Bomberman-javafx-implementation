package ir.ac.kntu.gameObject;

import ir.ac.kntu.animation.Dead;
import ir.ac.kntu.keyboard.KeyListener;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject implements KeyListener{
    private int num;
    private ArrayList<Image> images;
    private ArrayList<KeyCode> interestedInKeys;
    private int pastRowIndex;
    private int pastColumnIndex;
    private int power=3;
    private Direction direction= Direction.DOWN;
    private KeyEvent keyEvent;
    private PlayerInfo playerInfo;
    private boolean dying;

    public int getPastRowIndex() {
        return pastRowIndex;
    }

    public int getPastColumnIndex() {
        return pastColumnIndex;
    }

    public Direction getDirection() {
        return direction;
    }

    public Player(int num,PlayerInfo playerInfo,int rowIndex, int columnIndex) {
        super(rowIndex,columnIndex);
        this.playerInfo=playerInfo;
        setInfo(num,rowIndex,columnIndex);
    }

    public void setInfo(int num, int rowIndex, int columnIndex) {
        setRowIndex(rowIndex);
        setColumnIndex(columnIndex);
        playerInfo.plusNumOfGame();
        this.num=num;
        this.images=new ArrayList<>();
        interestedInKeys=new ArrayList<>();
        if(num==1){
            playerOneImages();
            interestedInKeys.add(KeyCode.UP);
            interestedInKeys.add(KeyCode.DOWN);
            interestedInKeys.add(KeyCode.LEFT);
            interestedInKeys.add(KeyCode.RIGHT);
        }
        if(num==2){
            playerTwoImages();
            interestedInKeys.add(KeyCode.A);
            interestedInKeys.add(KeyCode.S);
            interestedInKeys.add(KeyCode.D);
            interestedInKeys.add(KeyCode.W);
        }
    }
    /*private void playerFourImages() {
        try {
            images.add(new Image(new FileInputStream("src/main" +
                    "/resources/assets/player_yellow/player_yellow_down_moving.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_yellow/player_yellow_down_standing.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_yellow/player_yellow_left_moving.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_yellow/player_yellow_left_standing.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_yellow/player_yellow_right_moving.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_yellow/player_yellow_right_standing.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_yellow/player_yellow_up_moving.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_yellow/player_yellow_up_standing.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/
    private void playerTwoImages() {
        try {
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_black/player_black_down_moving.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_black/player_black_down_standing.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_black/player_black_left_moving.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_black/player_black_left_standing.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_black/player_black_right_moving.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_black/player_black_right_standing.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_black/player_black_up_moving.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_black/player_black_up_standing.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setImage(images.get(1));
    }
    private void playerOneImages(){
        try {
            images.add(new Image(new FileInputStream("src/main" +
                    "/resources/assets/player_red/player_red_down_moving.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_red/player_red_down-Standing.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_red/player_red_left_moving.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_red/player_red_left_standing.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_red/player_red_right_moving.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_red/player_red_right_standing.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_red/player_red_up_moving.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/player_red/player_red_up_standing.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setImage(images.get(1));
    }
    /*public void playerOneImages() {
        try {
            images.add(new Image(new FileInputStream("src/main/resources/assets/player/player_down_moving.png")));
            images.add(new Image(new FileInputStream("src/main/resources/assets/player/player_down_standing.png")));
            images.add(new Image(new FileInputStream("src/main/resources/assets/player/player_left_moving.png")));
            images.add(new Image(new FileInputStream("src/main/resources/assets/player/player_left_standing.png")));
            images.add(new Image(new FileInputStream("src/main/resources/assets/player/player_right_moving.png")));
            images.add(new Image(new FileInputStream("src/main/resources/assets/player/player_right_standing.png")));
            images.add(new Image(new FileInputStream("src/main/resources/assets/player/player_up_moving.png")));
            images.add(new Image(new FileInputStream("src/main/resources/assets/player/player_up_standing.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/
    @Override
    public void notify(KeyEvent keyEvent,List<GameObject> gameObjects){
        if(!isAlive()){
            return;
        }
        this.keyEvent=keyEvent;
        if(keyEvent.getEventType()==KeyEvent.KEY_RELEASED){
            setImage();
            return;
        }
        if(num==1) {
            this.pastColumnIndex=getColumnIndex();
            this.pastRowIndex=getRowIndex();
            notifyPlayer1(keyEvent,gameObjects);
        }
        if(num==2){
            this.pastColumnIndex=getColumnIndex();
            this.pastRowIndex=getRowIndex();
            notifyPlayer2(keyEvent,gameObjects);
        }
        setImage();
    }

    private void notifyPlayer2(KeyEvent keyEvent,List<GameObject> gameObjects) {
        switch (keyEvent.getCode()){
            case W:
                if(getRowIndex()>1) {
                    setRowIndex(getRowIndex() - 1);
                }
                this.direction= Direction.UP;
                break;
            case S:
                if (getRowIndex() < 9) {
                    setRowIndex(getRowIndex() + 1);
                }
                this.direction= Direction.DOWN;
                break;
            case D:
                if(getColumnIndex()<13) {
                    setColumnIndex(getColumnIndex() + 1);
                }
                this.direction= Direction.RIGHT;
                break;
            case A:
                if(getColumnIndex()>1) {
                    setColumnIndex(getColumnIndex() - 1);
                }
                this.direction= Direction.LEFT;
                break;
            case SPACE:
                Bomb bomb=new Bomb(getRowIndex(),getColumnIndex(),power);
                gameObjects.add(bomb);
                bomb.explosion(gameObjects);
        }
        gameObjects.remove(this);
        gameObjects.add(this);
    }

    private void notifyPlayer1(KeyEvent keyEvent,List<GameObject> gameObjects) {
        switch (keyEvent.getCode()){
            case UP:
                if(getRowIndex()>1) {
                    setRowIndex(getRowIndex() - 1);
                }
                this.direction= Direction.UP;
                break;
            case DOWN:
                if (getRowIndex() < 9) {
                    setRowIndex(getRowIndex() + 1);
                }
                this.direction= Direction.DOWN;
                break;
            case RIGHT:
                if(getColumnIndex()<13) {
                    setColumnIndex(getColumnIndex() + 1);
                }
                this.direction= Direction.RIGHT;
                break;
            case LEFT:
                if(getColumnIndex()>1) {
                    setColumnIndex(getColumnIndex() - 1);
                }
                this.direction= Direction.LEFT;
                break;
            case ENTER:
                Bomb bomb=new Bomb(getRowIndex(),getColumnIndex(),power);
                gameObjects.add(bomb);
                bomb.explosion(gameObjects);
        }
        gameObjects.remove(this);
        gameObjects.add(this);
    }
    public void goBack(){
        setColumnIndex(pastColumnIndex);
        setRowIndex(pastRowIndex);
    }
    public void collide(GameObject gameObject){
        if(gameObject instanceof Block ||gameObject instanceof Wall){
            goBack();
        }
        if(gameObject instanceof Flame){
            if(isAlive()&&!dying){
                dying=true;
                System.out.println("pibvf");
                playerInfo.plusNumOfLost();
                Thread thread=new Thread(new Dead(this));
                thread.start();
            }
        }
        if(gameObject instanceof Bomb){
            if(((Bomb) gameObject).isActive()){
                if(isAlive()&&!dying){
                    dying=true;
                    System.out.println("pibvf");
                    playerInfo.plusNumOfLost();
                    Thread thread=new Thread(new Dead(this));
                    thread.start();
                }
            }
        }
    }
    public void win(int score){
        playerInfo.win(score);
    }
    public void setDeadTime(int deadTime){
        playerInfo.setDeadTime(deadTime);
    }
    public void setImage(){
        if(keyEvent!=null&& keyEvent.getEventType()==KeyEvent.KEY_PRESSED
                &&interestedInKeys.contains(keyEvent.getCode())){
            switch (direction){
                case DOWN:
                    setImage(images.get(0));
                    break;
                case LEFT:
                    setImage(images.get(2));
                    break;
                case RIGHT:
                    setImage(images.get(4));
                    break;
                case UP:
                    setImage(images.get(6));
            }
        }else {
            switch (direction){
                case DOWN:
                    setImage(images.get(1));
                    break;
                case LEFT:
                    setImage(images.get(3));
                    break;
                case RIGHT:
                    setImage(images.get(5));
                    break;
                case UP:
                    setImage(images.get(7));
            }
        }
    }

    public void powerUp() {
        power=5;
    }

    public void powerDown() {
        power=3;
    }

    public int getNumOfLost() {
        return playerInfo.getNumOfLost();
    }
    public int getNumOfWon() {
        return playerInfo.getNumOfWon();
    }
    public int getNumOfGame() {
        return playerInfo.getNumOfGame();
    }

    public PlayerInfo getPlayerInfo() {
        return this.playerInfo;
    }
}
