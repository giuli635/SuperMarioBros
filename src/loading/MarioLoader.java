package loading;

import entities.Entity;
import entities.Mario;
import entities.UpdatableEntity;

public class MarioLoader implements EntityLoader {
    @Override
    public Entity load() {
        UpdatableEntity mario = new Mario();
        mario.load();
        return mario;
    }
}
