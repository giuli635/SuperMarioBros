package loading;

import entities.updateables.enemies.Goomba;
import game.LevelReader;

public class GoombaLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        Goomba goomba = new Goomba();
        positionCollider(goomba, lr.getRow(), lr.getColumn());
    }
}
