package ir.ac.kntu.gameObject;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class PowerUp extends GameObject {
    public PowerUp(int rowIndex, int columnIndex) {
        super(rowIndex,columnIndex);
        try {
            setImage(new Image(new FileInputStream("src/main/resources/assets/map/powerup.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void collide(GameObject gameObject){
        if(gameObject instanceof Player){
            collide((Player)gameObject);
        }
    }

    public void collide(Player player){
        player.powerUp();
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                player.powerDown();
                die();
                timer.cancel();
            }
        },15000);
        setVisible(false);
    }
}
