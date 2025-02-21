package loading;

import entities.updateables.enemies.Lakitu;
import game.LevelReader;

public class LakituLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        Lakitu lakitu = new Lakitu();
        positionCollider(lakitu, lr.getRow(), lr.getColumn());
    }
}
