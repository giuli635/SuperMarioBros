package loading;

import entities.enemies.Goomba;

public class GoombaLoader implements EntityLoader {
    @Override
    public Goomba load() {
        return new Goomba();
    }
}
