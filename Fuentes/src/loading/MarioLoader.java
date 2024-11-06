package loading;

import entities.updateables.mario.Mario;
import game.SingletonGame;
import game.LevelReader;

public class MarioLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        Mario mario = new Mario(SingletonGame.instance().getLevelStats());
        positionCollider(mario, lr.getRow(), lr.getColumn());
    }
}
