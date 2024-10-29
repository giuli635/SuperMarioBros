package loading;

import entities.solids.Brick;
import game.LevelReader;

public class BrickLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        Brick brick = new Brick();
        positionCollider(brick, lr.getRow(), lr.getColumn());
    }
}
