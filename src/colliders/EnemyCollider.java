package colliders;

import entities.Enemy;

public interface EnemyCollider extends UpdateableEntityCollider {
    public Enemy getEntity();    
}
