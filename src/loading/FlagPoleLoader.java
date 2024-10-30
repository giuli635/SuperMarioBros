package loading;

import entities.FlagPole;
import game.LevelReader;

public class FlagPoleLoader extends BaseLoader{
    @Override
    public void load(LevelReader lr) {
        FlagPole pole = new FlagPole();
        positionCollider(pole, lr.getRow(), lr.getColumn());
    }
    
}
