package loading;

import entities.Entity;
import entities.PiranhaPlant;

public class PiranhaPlantLoader implements EntityLoader{

    @Override
    public Entity load() {
       return new PiranhaPlant();
    }

}
