package loading;

import entities.mario.Mario;
import game.Game;

public class MarioLoader implements EntityLoader {
    @Override
    public Mario load() {
        Mario mario = new Mario(Game.instance().getLevelStats());
        mario.load();
        return mario;
    }
}
