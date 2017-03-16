package flappy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Android {
    private static Android android;
    BufferedImage droid;
    int x, y, width, height;


    static {
        android = new Android();
    }


    private Android() {
        x = 800 / 2 - 245;
        y = 470;
        width = 83;
        height = 90;
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheet = null;
        try {
            spriteSheet = loader.loadImage("/android.png");
        } catch (IOException e) {
            Logger.getLogger(Flappy.class.getName()).log(Level.SEVERE, null, e);
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        this.droid = ss.grabSprite(0, 0, width, height);
    }

    public static Android getAndroid(){
        return android;
    }


    public void paintAndroid(Graphics g) {
        g.drawImage(droid, x, y, width, height, null);
    }

}
