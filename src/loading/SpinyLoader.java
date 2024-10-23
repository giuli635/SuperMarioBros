package loading;

import entities.Spiny;

public class SpinyLoader implements EntityLoader {
    @Override
    public Spiny load() {
        return new Spiny();
    }
}
