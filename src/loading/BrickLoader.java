package loading;

import entities.Brick;
import game.LevelReader;

public class BrickLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        Brick brick = new Brick();
        positionCollider(brick, lr.getRow(), lr.getColumn());
    }
}
