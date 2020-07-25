package ir.ac.kntu.gameObject;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class PlayerInfo implements Serializable,Comparable<PlayerInfo> {
    private String name;
    private int numOfGame;
    private int numOfWon;
    private int numOfLost;
    private int score;
    private int lastScore;
    private int deadTime;

    public PlayerInfo(String name){
        this.name=name;
    }
    @Override
    public String toString() {
        return "name=" + name + "   " +
                "numOfGame=" + numOfGame +"   "+
                "numOfWon=" + numOfWon +"   "+
                "numOfLost=" + numOfLost+"   "+
                "score="+score;
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
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void win(int score){
        numOfWon++;
        lastScore=score;
        this.score+=score;
    }

    public String getName() {
        return name;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void plusNumOfLost(){
        lastScore=0;
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

    public void setDeadTime(int deadTime) {
        this.deadTime=deadTime;
    }

    @Override
    public int compareTo(@NotNull PlayerInfo o) {
        if(lastScore==o.lastScore){
            return deadTime-o.deadTime;
        }
        return lastScore-o.lastScore;
    }
}
