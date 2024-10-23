package loading;

import entities.Entity;
import entities.Lakitu;

public class LakituLoader implements EntityLoader {

    @Override
    public Entity load() {
        return new Lakitu();
    }

}
