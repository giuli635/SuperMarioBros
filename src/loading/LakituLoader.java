package loading;

import entities.enemies.Lakitu;

public class LakituLoader implements EntityLoader {
    @Override
    public Lakitu load() {
        return new Lakitu();
    }
}
