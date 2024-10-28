package colliders;

import java.awt.Point;
import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.EmptyBlockCollision;
import collisions.EnemyCollision;
import collisions.MarioCollision;
import collisions.PowerUpCollision;
import collisions.StarCollision;
import entities.EmptyBlock;
import entities.mario.Mario;
import game.GraphicEngine;

public class EmptyBlockCollider extends SolidCollider {
    protected EmptyBlock block;
    protected Rectangle bounds;
    protected Point previousPosition;
    protected boolean activated;

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