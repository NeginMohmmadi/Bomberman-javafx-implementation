package ir.ac.kntu.animation;

import ir.ac.kntu.gameObject.Block;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BlockBreak implements Runnable {
    private Block block;

    public BlockBreak(Block block){
        this.block=block;
    }
    @Override
    public void run() {
        try {
            try {
                block.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/blockBreak/block-break1.png")));
                Thread.sleep(80);
                block.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/blockBreak/block-break2.png")));
                Thread.sleep(80);
                block.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/blockBreak/block-break3.png")));
                Thread.sleep(80);
                block.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/blockBreak/block-break4.png")));
                Thread.sleep(80);
                block.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/blockBreak/block-break5.png")));
                Thread.sleep(80);
                block.setImage(new Image(new FileInputStream("src/main/" +
                        "resources/assets/map/blockBreak/block-break6.png")));
                Thread.sleep(80);
                block.die();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
