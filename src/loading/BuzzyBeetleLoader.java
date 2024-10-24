package loading;

import entities.BuzzyBeetle;

public class BuzzyBeetleLoader implements EntityLoader {
    @Override
    public BuzzyBeetle load() {
       return new BuzzyBeetle();
    }

}
