package loading;

import entities.solids.Block;
import game.LevelReader;

public class BlockLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        Block block = new Block();
        positionCollider(block, lr.getRow(), lr.getColumn());
    }
}
