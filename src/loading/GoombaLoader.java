package loading;

import entities.Goomba;

public class GoombaLoader implements EntityLoader {
    @Override
    public Goomba load() {
        return new Goomba();
    }
}
