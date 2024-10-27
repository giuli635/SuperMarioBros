package loading;

import entities.enemies.KoopaTroopa;

public class KoopaTroopaLoader implements EntityLoader {
    @Override
    public KoopaTroopa load() {
        return new KoopaTroopa();
    }
}
