package ir.ac.kntu.gameObject;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Flame extends GameObject {
    //private ArrayList<Image> images;
    private Direction direction;
    private Bomb bomb;
    //private int step;
    public Flame(int rowIndex, int columnIndex, Direction direction, Bomb bomb) {
        super(rowIndex, columnIndex);
        //images=new ArrayList<>();
        this.direction=direction;
        this.bomb=bomb;
        //this.step=step;
        //setImages();
        try {
            setImage(new Image(new FileInputStream("src/main/resources/assets/map/explosion/flame2.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setBounds(){
        switch (direction){
            case UP:
                bomb.setGoUp(false);
                break;
            case LEFT:
                bomb.setGoLeft(false);
                break;
            case DOWN:
                bomb.setGoDown(false);
                break;
            case RIGHT:
                bomb.setGoRight(false);
                break;
        }
    }

    /*private void setImages() {
        try {
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/map/explosion/flame2.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/map/explosion/flame2.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/map/explosion/flame2.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/map/explosion/flame2.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/map/explosion/flame2.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/map/explosion/flame2.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/map/explosion/flame2.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/map/explosion/flame2.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/map/explosion/flame2.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/map/explosion/flame2.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/map/explosion/flame2.png")));
            images.add(new Image(new FileInputStream("src/main/" +
                    "resources/assets/map/explosion/flame2.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Image getImage(){
        if(step==1){
            switch (direction){
                case DOWN:
                    return images.get(0);
                case LEFT:
                    return images.get(3);
                case RIGHT:
                    return images.get(6);
                case UP:
                    return images.get(9);
            }
        }else if(step==2){
            switch (direction){
                case DOWN:
                    return images.get(1);
                case LEFT:
                    return images.get(4);
                case RIGHT:
                    return images.get(7);
                case UP:
                    return images.get(10);
            }
        }else{
            switch (direction){
                case DOWN:
                    return images.get(2);
                case LEFT:
                    return images.get(5);
                case RIGHT:
                    return images.get(8);
                case UP:
                    return images.get(11);
            }
        }
        return null;
    }*/
    public Direction getDirection() {
        return this.direction;
    }

}
