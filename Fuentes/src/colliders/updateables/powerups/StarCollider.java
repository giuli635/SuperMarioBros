package colliders.updateables.powerups;

import java.awt.Rectangle;

import colliders.updateables.BouncerCollider;
import collisions.Collision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.StarMarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import collisions.updateables.powerups.StarCollision;
import entities.updateables.mario.Mario;
import entities.updateables.mario.states.StarMario;
import entities.updateables.powerups.Star;
import utils.Axis;

public class StarCollider extends PowerUpCollider implements BouncerCollider {
    protected Star s;

    public StarCollider(Star star, Rectangle b) {
        super(b);
        s = star;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
       c.collide(this, a);
    }

    @Override
    public StarCollision getCollision() {
        return new StarCollision(this);
    }

    @Override
    public Star getEntity() {
        return s;
    }

    @Override
    public void handleCollision(MarioCollision m) {
        super.handleCollision(m);
        Mario mario = m.getCollider().getEntity();
        mario.setState(new StarMario(mario));
        mario.modifyPoints(Star.POINTS_MARIO);
    }

    @Override
    public void handleCollision(SuperMarioCollision m) {
        super.handleCollision(m);
        Mario mario = m.getCollider().getEntity();
        mario.setState(new StarMario(mario));
        mario.modifyPoints(Star.POINTS_SUPER_MARIO);
    }

    public void handleCollision(StarMarioCollision m) {
        super.handleCollision(m);
        Mario mario = m.getCollider().getEntity();
        mario.setState(new StarMario(mario));
        mario.modifyPoints(Star.POINTS_STAR_MARIO);
    }
}
