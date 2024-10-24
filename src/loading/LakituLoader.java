package loading;

import entities.Lakitu;

public class LakituLoader implements EntityLoader {
    @Override
    public Lakitu load() {
        return new Lakitu();
    }
}
