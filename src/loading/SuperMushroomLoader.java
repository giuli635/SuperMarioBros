package loading;

import entities.powerUp.SuperMushroom;
import game.LevelReader;

public class SuperMushroomLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        SuperMushroom sm = new SuperMushroom();
        positionCollider(sm, lr.getRow(), lr.getColumn());
    }
}
