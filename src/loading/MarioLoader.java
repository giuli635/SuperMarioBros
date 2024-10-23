package loading;

import entities.Mario;

public class MarioLoader implements EntityLoader {
    @Override
    public Mario load() {
        Mario mario = new Mario();
        mario.load();
        return mario;
    }
}
