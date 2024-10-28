package loading;

import colliders.Collider;
import entities.Entity;
import game.LevelReader;

public abstract class BaseLoader implements EntityLoader{

    protected void positionCollider (Entity e, int row, int column) {
        Collider newEntityCollider = e.getCollider();
        newEntityCollider.setPosition(row * LevelReader.CHUNK, column * LevelReader.CHUNK);
        newEntityCollider.activate();
    }
    
}
