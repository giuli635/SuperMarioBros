package loading;

import entities.Entity;
import entities.Spiny;

public class SpinyLoader implements EntityLoader {
    @Override
    public Entity load() {
        return new Spiny();
    }
}
