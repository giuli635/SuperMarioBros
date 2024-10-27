package loading;

import entities.powerUp.SuperMushroom;

public class SuperMushroomLoader implements EntityLoader {
    @Override
    public SuperMushroom load() {
        return new SuperMushroom();
    }
}
