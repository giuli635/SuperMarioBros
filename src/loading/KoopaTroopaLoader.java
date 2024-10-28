package loading;

import entities.enemies.KoopaTroopa;
import game.LevelReader;

public class KoopaTroopaLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        KoopaTroopa koopa = new KoopaTroopa();
        positionCollider(koopa, lr.getRow(), lr.getColumn());
    }
}
