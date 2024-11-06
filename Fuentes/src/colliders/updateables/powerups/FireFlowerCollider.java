package colliders.updateables.powerups;

import java.awt.Rectangle;

import collisions.VisitorCollision;
import collisions.updateables.mario.FireMarioCollision;
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
    public void recieveCollision(VisitorCollision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public VisitorCollision getCollision() {
        return new FireFlowerCollision(this);
    }    

    @Override
    public FireFlower getEntity() {
       return f;
    }

    @Override
    public void handleCollision(FireMarioCollision m) {
        super.handleCollision(m);
        Mario mario = m.getCollider().getEntity();
        mario.modifyPoints(FireFlower.POINTS_FIRE_MARIO);
    }

    @Override
    public void handleCollision(SuperMarioCollision m) {
        super.handleCollision(m);
        Mario mario = m.getCollider().getEntity();
        mario.modifyPoints(FireFlower.POINTS_SUPER_MARIO);
        mario.setState(new FireMario(mario));
    }

    @Override
    public void handleCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        super.handleCollision(m);
        mario.modifyPoints(FireFlower.POINTS_MARIO);
        mario.setState(new FireMario(mario));
    }

}
