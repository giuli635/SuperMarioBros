package loading;

import entities.updateables.enemies.BuzzyBeetle;
import game.LevelReader;

public class BuzzyBeetleLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        BuzzyBeetle buzzy = new BuzzyBeetle();
        positionCollider(buzzy, lr.getRow(), lr.getColumn());
    }

}
