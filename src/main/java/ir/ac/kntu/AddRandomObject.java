package ir.ac.kntu;

import ir.ac.kntu.gameObject.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AddRandomObject extends TimerTask {
    private List<GameObject> gameObjects;
    private static final SecureRandom GENERATOR = new SecureRandom();
    private Timer timer;

    public AddRandomObject(List<GameObject> gameObjects) {
        this.gameObjects=gameObjects;
        this.timer=new Timer();
    }

    @Override
    public void run() {
        randomObject();
    }

    public void go(){
        Timer timer=new Timer();
        timer.schedule(this,15000,15000);
    }

    public void stop(){
        timer.cancel();
    }
    public void randomObject(){
        Cell cell=randomCell();
        int random=GENERATOR.nextInt(3);
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
        for (int i=0;i<gameObjects.size();i++){
            if(gameObjects.get(i) instanceof Ground){
                Cell cell=new Cell(gameObjects.get(i).getRowIndex()
                        ,gameObjects.get(i).getColumnIndex());
                cells.add(cell);
            }
        }
        return cells.get(GENERATOR.nextInt(cells.size()));
    }
}
