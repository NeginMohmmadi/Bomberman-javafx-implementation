package ir.ac.kntu.map;

import ir.ac.kntu.gameObject.GameObject;

public interface GameObjectCreator {
    public GameObject create(int rowIndex, int columnIndex);
}
