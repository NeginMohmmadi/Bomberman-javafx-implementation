package ir.ac.kntu.gameObject;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Wall extends GameObject {
    public Wall(int rowIndex, int columnIndex) {
        super(rowIndex,columnIndex);
        try {
            setImage(new Image(new FileInputStream("src/main/resources/assets/map/wall.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void collide(GameObject gameObject){
        if(gameObject instanceof Flame){
            collide((Flame)gameObject);
        }
    }

    public void collide(Flame flame){
        //flame.setBounds();
        flame.die();
    }
}
