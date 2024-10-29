package collisions.updateables.enemies;

import colliders.updateables.enemies.EnemyCollider;
import collisions.updateables.UpdateableEntityCollision;

public interface EnemyCollision extends UpdateableEntityCollision{
    public EnemyCollider getCollider();
}
