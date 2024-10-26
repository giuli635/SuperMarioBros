package collisions;

import colliders.EnemyCollider;

public interface EnemyCollision extends UpdateableEntityCollision{
    public EnemyCollider getCollider();
}
