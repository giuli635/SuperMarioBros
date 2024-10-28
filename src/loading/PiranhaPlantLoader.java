package loading;

import entities.enemies.PiranhaPlant;
import game.LevelReader;

public class PiranhaPlantLoader extends BaseLoader {

    @Override
    public void load(LevelReader lr) {
       PiranhaPlant piranha = new PiranhaPlant();
       positionCollider(piranha, lr.getRow(), lr.getColumn());
    }

}
