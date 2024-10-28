package loading;

import entities.EmptyBlock;

public class EmptyBlockLoader implements EntityLoader {

    @Override
    public EmptyBlock load() {
        return new EmptyBlock();
    }

}
