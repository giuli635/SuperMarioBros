package loading;

import entities.mario.Mario;

public class MarioLoader implements EntityLoader {
    @Override
    public Mario load() {
        Mario mario = new Mario();
        mario.load();
        return mario;
    }
}
