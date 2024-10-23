package loading;

import entities.Brick;

public class BrickLoader implements EntityLoader{
    @Override
    public Brick load() {
        return new Brick();
    }
}
