package loading;

import entities.ConfigurationBlock;
import game.LevelReader;

public class ConfigurationBlockLoader extends BaseLoader{
    @Override
    public void load(LevelReader lr) {
        lr.setColumn(lr.getColumn() + 1);
        char character = lr.getChunk().charAt(lr.getColumn());
        ConfigurationBlock config = new ConfigurationBlock(character);
        positionCollider(config, lr.getRow(), lr.getColumn() - 1);
    }
}
