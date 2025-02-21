package loading;

import entities.updateables.enemies.PiranhaPlant;
import game.LevelReader;

public class PiranhaPlantLoader extends BaseLoader {
   protected static final int DESPLAZAMIENTO_ADICIONAL = 2;

   @Override
   public void load(LevelReader lr) {
      PiranhaPlant piranha = new PiranhaPlant();
      positionCollider(piranha, lr.getRow(), lr.getColumn() - 1);
      int heightDiff = piranha.getGraphicElement().getSprite().getIconHeight() - LevelReader.CHUNK + DESPLAZAMIENTO_ADICIONAL;
      piranha.getCollider().translate(LevelReader.CHUNK / 2, -heightDiff);
   }
}
