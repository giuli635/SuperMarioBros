package game;
import java.awt.Image;
import java.time.Duration;

public class Level {
    protected Duration time;
    protected int points;
    protected Image background;

    public Level(Duration t) {
        time = t;
    }

    public int getPoints(){
        return points;
    }

    public void addPoints(int p){
        points += p;
    }

    public void substractPoints(int p){
        points -= p;
    }
}
