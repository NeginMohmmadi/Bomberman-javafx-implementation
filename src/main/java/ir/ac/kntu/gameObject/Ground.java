package ir.ac.kntu.gameObject;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Ground extends GameObject {
    public Ground(int rowIndex, int columnIndex) {
        super(rowIndex, columnIndex);
        try {
            setImage(new Image(new FileInputStream("src/main/resources/assets/map/ground.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
