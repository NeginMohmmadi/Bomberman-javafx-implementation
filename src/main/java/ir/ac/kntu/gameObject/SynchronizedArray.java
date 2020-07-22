package ir.ac.kntu.gameObject;

import java.util.ArrayList;
import java.util.Collection;

public class SynchronizedArray<T> extends ArrayList<T> {

    @Override
    public synchronized boolean add(T t){
        return super.add(t);
    }

    public synchronized void add(int index,T t){
        super.add(index,t);
    }
    @Override
    public synchronized T remove(int index){
        return super.remove(index);
    }
    @Override
    public synchronized boolean remove(Object o){
        return super.remove(o);
    }
    @Override
    public synchronized boolean removeAll(Collection<?> collection){
        return super.removeAll(collection);
    }

    /*public synchronized GameObje get(int rowIndex,int columnIndex){
        for(GameObject gameObject : gameObjects){
            if(gameObject.getColumnIndex()==columnIndex && gameObject.getRowIndex()==rowIndex){
                return gameObject;
            }
        }
        return null;
    }

    public ArrayList<Cell> getGroundInstance(){
        ArrayList<Cell> cells=new ArrayList<>();
        for (GameObject gameObject : gameObjects){
            if(gameObject instanceof Ground){
                Cell cell=new Cell(gameObject.getRowIndex(),gameObject.getColumnIndex());
                cells.add(cell);
            }
        }
        return cells;
    }

    public ArrayList<GameObject> getKeyListenerInstance(){
        ArrayList<GameObject> result=new ArrayList<>();
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof KeyListener) {
                result.add(gameObject);
            }
        }
        return result;
    }*/
}
