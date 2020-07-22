package ir.ac.kntu.keyboard;

import ir.ac.kntu.gameObject.GameObject;
import javafx.scene.input.KeyEvent;

import java.util.List;

public interface KeyListener {
    public void notify(KeyEvent keyEvent, List<GameObject> gameObjects);
}
