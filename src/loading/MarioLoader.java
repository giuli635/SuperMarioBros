package loading;

import entities.updateables.mario.Mario;
import game.Game;
import game.LevelReader;

public class MarioLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        Mario mario = new Mario(Game.instance().getLevelStats());
        positionCollider(mario, lr.getRow(), lr.getColumn());
        mario.load();
    }
}
