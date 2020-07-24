package ir.ac.kntu.gameObject;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        PlayerInfo that = (PlayerInfo) o;
        return numOfGame == that.numOfGame &&
                numOfWon == that.numOfWon &&
                numOfLost == that.numOfLost &&
                score == that.score &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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
