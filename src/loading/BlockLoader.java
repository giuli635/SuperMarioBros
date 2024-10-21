package loading;

import entities.Block;
import entities.Entity;

public class BlockLoader implements EntityLoader {
    @Override
    public Entity load() {
        return new Block();
    }
}
