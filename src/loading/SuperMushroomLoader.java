package loading;

import entities.SuperMushroom;

public class SuperMushroomLoader implements EntityLoader {
    @Override
    public SuperMushroom load() {
        return new SuperMushroom();
    }
}
