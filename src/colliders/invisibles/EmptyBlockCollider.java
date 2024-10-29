package colliders.invisibles;

import java.awt.Point;
import java.awt.Rectangle;

import colliders.solids.SolidCollider;
import collisions.Collision;
import collisions.invisibles.EmptyBlockCollision;
import collisions.updateables.enemies.EnemyCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.powerups.PowerUpCollision;
import collisions.updateables.powerups.StarCollision;
import entities.EmptyBlock;
import entities.updateables.mario.Mario;
import game.GraphicEngine;
import utils.Axis;

public class EmptyBlockCollider extends SolidCollider {
    protected EmptyBlock block;

    public EmptyBlockCollider(EmptyBlock e, Rectangle b) {
        super(b);
        block = e;
    }

    public EmptyBlock getEntity() {
        return block;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public EmptyBlockCollision getCollision() {
        return new EmptyBlockCollision(this);
    }

    public void handleVerticalCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        mario.die();
    }

    public void handleHorizontalCollision(EnemyCollision e) {
        e.getCollider().getEntity().recieveDamage();
    }

    public void handleVerticalCollision(EnemyCollision e) {
        e.getCollider().getEntity().recieveDamage();
    }

    public void handleHorizontalCollision(PowerUpCollision p) {
        p.getCollider().deactivate();
        p.getCollider().getEntity().unload();
        GraphicEngine.instance().remove(p.getCollider().getEntity().getGraphicElement());
    }

    public void handleVerticalCollision(PowerUpCollision p) {
        p.getCollider().deactivate();
        p.getCollider().getEntity().unload();
        GraphicEngine.instance().remove(p.getCollider().getEntity().getGraphicElement());
    }

    public void handleVerticalCollision(StarCollision s) {
        s.getCollider().deactivate();
        s.getCollider().getEntity().unload();
        GraphicEngine.instance().remove(getEntity().getGraphicElement());
    }
}
