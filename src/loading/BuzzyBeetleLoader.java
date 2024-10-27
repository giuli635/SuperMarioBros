package loading;

import entities.enemies.BuzzyBeetle;

public class BuzzyBeetleLoader implements EntityLoader {
    @Override
    public BuzzyBeetle load() {
       return new BuzzyBeetle();
    }

}
