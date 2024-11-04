package collisions.updateables.enemies;

import colliders.updateables.enemies.EnemyCollider;
import collisions.updateables.MovableEntityCollision;

public interface EnemyCollision extends MovableEntityCollision {
    public EnemyCollider getCollider();
}
