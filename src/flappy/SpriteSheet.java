package flappy;


import java.awt.image.BufferedImage;

public class SpriteSheet {
    public BufferedImage spriteSheet;

    public SpriteSheet(BufferedImage ss) {
        this.spriteSheet = ss;
    }

    public BufferedImage grabSprite(int x, int y, int width, int heigth) {
        BufferedImage sprite = spriteSheet.getSubimage(x, y, width, heigth);
        return sprite;
    }
}
