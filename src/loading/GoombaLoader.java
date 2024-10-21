package loading;

import entities.Entity;
import entities.Goomba;

public class GoombaLoader implements EntityLoader {
    @Override
    public Entity load() {
        return new Goomba();
    }
}
