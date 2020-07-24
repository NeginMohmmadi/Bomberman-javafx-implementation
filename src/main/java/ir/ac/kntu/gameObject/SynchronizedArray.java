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
}
