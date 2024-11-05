package loading;

import entities.updateables.powerups.GreenMushroom;
import game.LevelReader;

public class GreenMushroomLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        GreenMushroom green = new GreenMushroom();
        positionCollider(green, lr.getRow(), lr.getColumn());
    }
}
