package flappy;

/**
 * Created by Илья on 27.05.2016.
 */
public class ColumnCreator implements Creator {
    @Override
    public Obstacle factoryMethod() {
        return new Column();
    }
}
