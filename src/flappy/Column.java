package flappy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Илья on 27.05.2016.
 */
public class Column extends Obstacle {
    /**
     * Method init columns
     *
     * @param s
     * @see Obstacle#
     */
    public Column() {
        super(500,"/marshmallow.png");
    }
}
