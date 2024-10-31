package loading;

import entities.updateables.powerups.Star;
import game.LevelReader;

public class StarLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        Star star = new Star();
        positionCollider(star, lr.getRow(), lr.getColumn());
    }
}
