package ir.ac.kntu.animation;

import ir.ac.kntu.gameObject.Player;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Dead implements Runnable {
    private Player player;

    public Dead(Player player){
        this.player=player;
    }

    @Override
    public void run() {
        try {
            try {
                Thread.sleep(480);
                player.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/dead-player-black/dead1.png")));
                Thread.sleep(150);
                player.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/dead-player-black/dead2.png")));
                Thread.sleep(150);
                player.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/dead-player-black/dead3.png")));
                Thread.sleep(150);
                player.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/dead-player-black/dead4.png")));
                Thread.sleep(150);
                player.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/dead-player-black/dead5.png")));
                Thread.sleep(150);
                player.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/dead-player-black/dead6.png")));
                Thread.sleep(150);
                player.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/dead-player-black/dead7.png")));
                Thread.sleep(150);
                player.die();

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
