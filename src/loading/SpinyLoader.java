package loading;

import entities.enemies.Spiny;
import game.LevelReader;

public class SpinyLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        Spiny spiny = new Spiny();
        positionCollider(spiny, lr.getRow(), lr.getColumn());
    }
}
