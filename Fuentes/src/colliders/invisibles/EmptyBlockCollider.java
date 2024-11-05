package colliders.invisibles;

import java.awt.Rectangle;

import colliders.BaseCollider;
import collisions.Collision;
import collisions.invisibles.EmptyBlockCollision;
import collisions.updateables.FireBallCollision;
import collisions.updateables.UpdateableEntityCollision;
import collisions.updateables.mario.MarioCollision;
import entities.EmptyBlock;
import entities.updateables.mario.Mario;
import utils.Axis;

public class EmptyBlockCollider extends BaseCollider {
    protected EmptyBlock block;

    public EmptyBlockCollider(EmptyBlock e, Rectangle b) {
        super(b);
        block = e;
    }

    public EmptyBlock getEntity() {
        return block;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public EmptyBlockCollision getCollision() {
        return new EmptyBlockCollision(this);
    }

    public void handleVerticalCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        mario.modifyPoints(EmptyBlock.POINTS);
        mario.die();
    }

    public void handleHorizontalCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        mario.modifyPoints(EmptyBlock.POINTS);
        mario.die();
    }

    public void handleHorizontalCollision(UpdateableEntityCollision p) {
        p.getCollider().deactivate();
        p.getCollider().getEntity().unload();
        p.getCollider().getEntity().getGraphicElement().remove();
    }

    public void handleVerticalCollision(UpdateableEntityCollision p) {
        p.getCollider().deactivate();
        p.getCollider().getEntity().unload();
        p.getCollider().getEntity().getGraphicElement().remove();
    }

    public void handleHorizontalCollision(FireBallCollision f) {
        f.getCollider().getEntity().destroy();
    }

    public void handleVerticalCollision(FireBallCollision f) {
        f.getCollider().getEntity().destroy();
    }
}
