package ir.ac.kntu;

import ir.ac.kntu.gameObject.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class AddRandomObject implements Runnable {
    private List<GameObject> gameObjects;
    private static final SecureRandom generator = new SecureRandom();

    public AddRandomObject(List<GameObject> gameObjects) {
        this.gameObjects=gameObjects;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            randomObject();
        }
    }
    public void randomObject(){
        Cell cell=randomCell();
        int random=generator.nextInt(3);
        if (random==0){
            gameObjects.add(new Bomb(cell.getRowIndex(),cell.getColumnIndex(),3));
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
