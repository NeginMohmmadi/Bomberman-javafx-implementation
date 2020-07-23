package ir.ac.kntu.keyboard;

import ir.ac.kntu.gameObject.GameObject;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class KeyLogger implements EventHandler<KeyEvent> {
    private ArrayList<KeyListener> listeners;
    private List<GameObject> gameObjects;

    public KeyLogger(Scene scene, List<GameObject> gameObjects){
        scene.setOnKeyPressed(this);
        scene.setOnKeyReleased(this);
        this.gameObjects=gameObjects;
        listeners=new ArrayList<>();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        for(KeyListener listener: listeners){
            listener.notify(keyEvent,gameObjects);
        }
    }

    public void addListener(KeyListener keyListener){
        if(!listeners.contains(keyListener)){
            listeners.add(keyListener);
        }
    }

    public void removeListener(GameObject gameObject) {
        if(gameObject instanceof KeyListener){
            listeners.remove(gameObject);
        }
    }
}