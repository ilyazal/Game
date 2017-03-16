package flappy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Background {
    BufferedImage bg;
    int x, y;

    public Background() {
        x = 0;
        y = 0;
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheet = null;
        try {
            spriteSheet = loader.loadImage("/background.png");
        } catch (IOException e) {
            Logger.getLogger(Flappy.class.getName()).log(Level.SEVERE, null, e);
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        this.bg = ss.grabSprite(0, 0, Renderer.WIDTH, Renderer.HEIGHT);
    }

    public void paintBackground(Graphics g, int speed) {
        if (x >= 799) {
            x = 0;
        }
        g.drawImage(bg, -x, y, Renderer.WIDTH, Renderer.HEIGHT, null);
        g.drawImage(bg, 799 - x, y, Renderer.WIDTH, Renderer.HEIGHT, null);
        if (speed != 0) {
            x += 1 + speed - 10;
        }
    }
}
