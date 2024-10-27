package loading;

import entities.enemies.Spiny;

public class SpinyLoader implements EntityLoader {
    @Override
    public Spiny load() {
        return new Spiny();
    }
}
