package loading;

import entities.EmptyBlock;
import game.LevelReader;

public class EmptyBlockLoader extends BaseLoader {

    @Override
    public void load(LevelReader lr) {
        EmptyBlock empty = new EmptyBlock();
        positionCollider(empty, lr.getRow(), lr.getColumn());
    }

}
