package colliders.solids;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import collisions.Collision;
import collisions.solids.BrickCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import entities.solids.Brick;
import entities.updateables.mario.Mario;
import utils.Axis;

public class BrickCollider extends SolidCollider {
    protected Brick brick;

    public BrickCollider(Brick brick2, Rectangle b) {
        super(b);
        brick = brick2;
    }

    public Brick getEntity() {
        return brick;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public BrickCollision getCollision() {
        return new BrickCollision(this);
    }

    public void handleVerticalCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        int displacement = displaceVertically(m.getCollider());
        
        if (displacement >= 0) {
            mario.land();
        } else {
            translate(0, -displacement);
            getEntity().getGraphicElement().translate(0, -displacement);
                    
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run(){
                    translate(0, displacement);
                    getEntity().getGraphicElement().translate(0, displacement);
                }
            };

            timer.schedule(task,300);
        }
    }

    public void handleVerticalCollision(SuperMarioCollision m) {
        int displacement = displaceVertically(m.getCollider());
        Mario mario = m.getCollider().getEntity();
        if (displacement < 0) {
            brick.getCollider().deactivate();
            brick.getGraphicElement().remove();
            mario.setSpeedY(0);
        }
        else{
            mario.land();
        }
    }
}
