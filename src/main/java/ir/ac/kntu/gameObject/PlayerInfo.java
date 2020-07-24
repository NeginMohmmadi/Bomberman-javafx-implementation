package ir.ac.kntu.gameObject;

import java.io.Serializable;

public class PlayerInfo implements Serializable {
    private String name;
    private int numOfGame;
    private int numOfWon;
    private int numOfLost;
    private int score;

    public PlayerInfo(String name){
        this.name=name;
    }
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + "   " +
                ", numOfGame=" + numOfGame +"   "+
                ", numOfWon=" + numOfWon +"   "+
                ", numOfLost=" + numOfLost;
    }

    public void win(){
        numOfWon++;
    }

    public void plusNumOfLost(){
        numOfLost++;
    }

    public void plusNumOfGame(){
        numOfGame++;
    }

    public void plusNumOfWon(){
        numOfWon++;
    }

    public int getNumOfGame() {
        return numOfGame;
    }

    public int getNumOfLost() {
        return numOfLost;
    }

    public int getNumOfWon() {
        return numOfWon;
    }
}
