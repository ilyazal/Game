package flappy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Obstacle {
    BufferedImage mm;
    int x, y, width, height;
    Rectangle andr, cl;

    public Obstacle(int y,String s) {
        Save.value();
        if (Save.x == 0) {
            Random rand = new Random();
            int offset = rand.nextInt(50);
            if (offset >= 0 && offset <= 50) {
                offset = 50;
            }
            else if (offset >= 50 && offset <= 100) {
                offset = 100;
            }
            else if (offset >= 100 && offset <= 150) {
                offset = 150;
            }
            else if (offset >= 150 && offset <= 200) {
                offset = 200;
            }
            else if (offset >= 200 && offset <= 250) {
                offset = 250;
            }
            else if (offset >= 250 && offset <= 300) {
                offset = 300;
            }
            x = 800 + offset;
        } else {
            x = Save.x;
        }
        this.y=y;
        width = 67;
        height = 147;

        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheet = null;
        try {
            spriteSheet = loader.loadImage(s);
        } catch (IOException e) {
            Logger.getLogger(Flappy.class.getName()).log(Level.SEVERE, null, e);
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        this.mm = ss.grabSprite(0, 0, width, height);
        Save.record(x);

    }




    public void paintObstacle(Graphics g) {
        g.drawImage(mm, x, y, width, height, null);
    }


    public boolean intersects(Android droid) {
        andr = new java.awt.Rectangle(droid.x, droid.y, droid.width, droid.height);
        cl = new java.awt.Rectangle(x, y, width, height);
        if (cl.intersects(andr)) {
            return true;
        }
        return false;
    }

}
