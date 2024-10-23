package loading;

import entities.KoopaTroopa;

public class KoopaTroopaLoader implements EntityLoader {
    @Override
    public KoopaTroopa load() {
        return new KoopaTroopa();
    }
}
