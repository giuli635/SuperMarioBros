package loading;

import entities.updateables.enemies.PiranhaPlant;
import game.LevelReader;

public class PiranhaPlantLoader extends BaseLoader {
   @Override
   public void load(LevelReader lr) {
      PiranhaPlant piranha = new PiranhaPlant();
      positionCollider(piranha, lr.getRow(), lr.getColumn());
      int heightDiff = piranha.getGraphicElement().getSprite().getIconHeight() - LevelReader.CHUNK + 2;
      piranha.getCollider().translate(LevelReader.CHUNK / 2, -heightDiff);
   }
}
