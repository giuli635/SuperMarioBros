package loading;

import entities.Entity;
import entities.SuperMushroom;

public class SuperMushroomLoader implements EntityLoader {
    @Override
    public Entity load() {
        return new SuperMushroom();
    }
}
