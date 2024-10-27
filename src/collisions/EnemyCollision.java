package collisions;

import colliders.enemies.EnemyCollider;

public interface EnemyCollision extends UpdateableEntityCollision{
    public EnemyCollider getCollider();
}
