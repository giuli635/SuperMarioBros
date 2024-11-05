package loading;

import entities.updateables.powerups.FireFlower;
import game.LevelReader;

public class FireFlowerLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        FireFlower flower = new FireFlower();
        positionCollider(flower, lr.getRow(), lr.getColumn());
    }
}
