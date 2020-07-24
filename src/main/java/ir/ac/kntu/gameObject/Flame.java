package ir.ac.kntu.gameObject;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Flame extends GameObject {
    private Direction direction;
    private int step;
    private int stage;
    private int power;
    public Flame(int rowIndex, int columnIndex, Direction direction,int power,int step,int stage) {
        super(rowIndex, columnIndex);
        //images=new ArrayList<>();
        this.direction=direction;
        this.step=step;
        this.stage=stage;
        this.power=power;
        try {
            setImage(new Image(new FileInputStream("src/main/resources/assets/map/explosion/flame2.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Image getImage(){
        switch (direction){
            case RIGHT:
                return getRightImage();
            case DOWN:
                return getDownImage();
            case UP:
                return getUpImage();
            case LEFT:
                return getLeftImage();
        }
        return null;
    }

    private Image getRightImage() {
        try {
            switch (stage) {
                case 1:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion28.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion12.png"));
                    }
                case 2:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion27.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion11.png"));
                    }
                case 3:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion26.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion10.png"));
                    }
                default:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion25.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion9.png"));
                    }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
    private Image getLeftImage() {
        try {
            switch (stage) {
                case 1:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion28.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion20.png"));
                    }
                case 2:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion27.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion19.png"));
                    }
                case 3:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion26.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion18.png"));
                    }
                default:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion25.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion17.png"));
                    }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
    private Image getUpImage() {
        try {
            switch (stage) {
                case 1:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion24.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion8.png"));
                    }
                case 2:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion23.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion7.png"));
                    }
                case 3:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion22.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion6.png"));
                    }
                default:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion21.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion5.png"));
                    }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    private Image getDownImage() {
        try {
            switch (stage) {
                case 1:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion24.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion16.png"));
                    }
                case 2:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion23.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/exolosion15.png"));
                    }
                case 3:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion22.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion14.png"));
                    }
                default:
                    if (step < power) {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion21.png"));
                    } else {
                        return new Image(new FileInputStream("src/main/" +
                                "resources/assets/map/myExplision/explosion13.png"));
                    }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public Direction getDirection() {
        return this.direction;
    }

}
