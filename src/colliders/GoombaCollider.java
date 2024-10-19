package colliders;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.GoombaCollision;
import entities.Enemy;
import entities.Entity;

public class GoombaCollider extends BaseCollider {
    protected Enemy goomba;

    public GoombaCollider(Enemy g, Rectangle b) {
        super(b);
        goomba=g;
    }

    @Override
    public Entity getEntity() {
        return goomba;
    }

    @Override
    public void sendCollision(Collision c) {
        c.collide(this);
    }

    @Override
    public Collision getCollision() {
        return new GoombaCollision(this);
    }

    

}
