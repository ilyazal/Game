package flappy;


import java.awt.*;
import java.io.*;

public class Achievements implements Observer {
    private String s;
    private int score;
    private boolean open=false;

    public Achievements(String s, int score){
        this.s=s;
        this.score = score;
    }



    @Override
    public void update(int score) {
        if (score ==this.score) {
            open = true;
        }
    }

    public boolean isOpen() {
        return open;
    }

    public String getAchievementsName() {
        String s1=s;
        if(open)
            s1+=" + ";
        return s1;
    }
}
