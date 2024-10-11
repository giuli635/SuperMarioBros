import java.awt.Image;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
// TODO: agregar el constructor

public class Level {
    protected Collection<Entity>[][] levelEntites;
    protected Duration time;
    protected int points;
    protected Image background;

    @SuppressWarnings("unchecked")
    public Level(Duration t) {
        time = t;
        levelEntites = new ArrayList[15][100];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 100; j++) {

            }
        }
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

    // TODO: check out of bounds
    public void setChunk(Entity e, int x, int y) {
        levelEntites[x][y].add(e);
    }

    // TODO: check out of bounds
    public Iterator<Entity> getChunk(int x, int y) {
        return levelEntites[x][y].iterator();
    }
}
