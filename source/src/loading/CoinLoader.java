package loading;

import entities.updateables.Coin;
import game.LevelReader;

public class CoinLoader extends BaseLoader {
    @Override
    public void load(LevelReader lr) {
        Coin flower = new Coin();
        positionCollider(flower, lr.getRow(), lr.getColumn());
    }
}
