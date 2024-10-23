package loading;

import entities.Block;

public class BlockLoader implements EntityLoader {
    @Override
    public Block load() {
        return new Block();
    }
}
