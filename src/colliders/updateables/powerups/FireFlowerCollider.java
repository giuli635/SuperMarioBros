package colliders.updateables.powerups;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import collisions.updateables.powerups.FireFlowerCollision;
import entities.updateables.mario.Mario;
import entities.updateables.mario.states.FireMario;
import entities.updateables.powerups.FireFlower;
import utils.Axis;

public class FireFlowerCollider extends PowerUpCollider {
    protected FireFlower f;

    public FireFlowerCollider(FireFlower fireFlower, Rectangle b) {
        super(b);
        f = fireFlower;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public Collision getCollision() {
        return new FireFlowerCollision(this);
    }    

    @Override
    public FireFlower getEntity() {
       return f;
    }

    // @Override
    // public void handleCollision(FireMarioCollision m) {
    // }

    @Override
    public void handleCollision(SuperMarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        super.handleCollision(m);
        mario.setState(new FireMario(mario));
    }

    @Override
    public void handleCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        super.handleCollision(m);
        mario.setState(new FireMario(mario));
    }
}
