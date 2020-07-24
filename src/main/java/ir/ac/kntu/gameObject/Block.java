package ir.ac.kntu.gameObject;

import ir.ac.kntu.animation.BlockBreak;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Block extends GameObject {
    private boolean dying;
    public Block(int rowIndex, int columnIndex) {
        super(rowIndex,columnIndex);
        try {
            setImage(new Image(new FileInputStream("src/main/resources/assets/map/block.png")));
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
        if(!dying) {
            dying=true;
            Thread thread = new Thread(new BlockBreak(this));
            thread.start();
        }
    }
}
