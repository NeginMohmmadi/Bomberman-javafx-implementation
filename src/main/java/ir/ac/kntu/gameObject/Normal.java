package ir.ac.kntu.gameObject;

import ir.ac.kntu.GameLoop;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Normal extends GameObject {
    public Normal(int rowIndex, int columnIndex) {
        super(rowIndex, columnIndex);
        try {
            setImage(new Image(new FileInputStream("src/main/resources/assets/map/ground.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
