package ir.ac.kntu.gameObject;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class OneWay extends GameObject {
    private Direction direction;
    private ArrayList<Image> images;

    public OneWay(Direction direction, int rowIndex, int columnIndex) {
        super(rowIndex,columnIndex);
        images=new ArrayList<>();
        this.direction=direction;
        try {
            setImage(direction);
        }catch (FileNotFoundException e) {
                e.printStackTrace();
        }
    }
    public void setImage(Direction direction) throws FileNotFoundException {
        switch (direction) {
            case UP:
                super.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/oneway/oneway_up.png")));
                break;
            case DOWN:
                super.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/oneway/oneway_down.png")));
                break;
            case RIGHT:
                super.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/oneway/oneway_right.png")));
                break;
            case LEFT:
                super.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/oneway/oneway_left.png")));
                break;
        }
    }
    public void collide(GameObject gameObject){
        if(gameObject instanceof Player){
            collide((Player)gameObject);
        }
    }

    public void collide(Player player){
        if(player.getDirection()!=direction){
            player.goBack();
        }
    }
}
