package loading;

import entities.solids.Castle;
import game.LevelReader;

public class CastleLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        char character = lr.getChunk().charAt(lr.getColumn());
        Castle castle = new Castle(character);
        positionCollider(castle, lr.getRow(), lr.getColumn());
    }
}
