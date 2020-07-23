package ir.ac.kntu;

import ir.ac.kntu.gameObject.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AddRandomObject extends TimerTask {
    private List<GameObject> gameObjects;
    private boolean end;
    private static final SecureRandom generator = new SecureRandom();

    public AddRandomObject(List<GameObject> gameObjects) {
        this.gameObjects=gameObjects;
    }

    public void setEnd(boolean end){
        this.end=end;
    }

    @Override
    public void run() {
        randomObject();
    }

    public void go(){
        Timer timer=new Timer();
        timer.schedule(this,15000,15000);
    }
    public void randomObject(){
        Cell cell=randomCell();
        int random=generator.nextInt(3);
        if (random==0){
            Bomb bomb=new Bomb(cell.getRowIndex(),cell.getColumnIndex(),3);
            gameObjects.add(bomb);
            bomb.explosion(gameObjects);

        }else if(random==1){
            gameObjects.add(new OneWay(Direction.LEFT,cell.getRowIndex(),cell.getColumnIndex()));
        }else{
            gameObjects.add(new PowerUp(cell.getRowIndex(),cell.getColumnIndex()));
        }
    }

    public Cell randomCell(){
        ArrayList<Cell> cells=new ArrayList<>();
        for (GameObject gameObject : gameObjects){
            if(gameObject instanceof Ground){
                Cell cell=new Cell(gameObject.getRowIndex(),gameObject.getColumnIndex());
                cells.add(cell);
            }
        }

        return cells.get(generator.nextInt(cells.size()));
    }
}
