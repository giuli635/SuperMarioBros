package loading;

import entities.Entity;
import entities.KoopaTroopa;

public class KoopaTroopaLoader implements EntityLoader {
    @Override
    public Entity load() {
        return new KoopaTroopa();
    }
}
