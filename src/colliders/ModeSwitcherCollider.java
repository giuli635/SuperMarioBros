package colliders;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import colliders.solids.SolidCollider;
import collisions.Collision;
import collisions.ModeSwitcherCollision;
import collisions.updateables.mario.MarioCollision;
import entities.Entity;
import entities.solids.QuestionBlock;
import entities.updateables.mario.Mario;
import game.GraphicEngine;
import utils.Axis;

public class ModeSwitcherCollider extends SolidCollider {
    protected QuestionBlock block;

    public ModeSwitcherCollider(QuestionBlock q, Rectangle b) {
        super(b);
        block = q;
    }

    @Override
    public Entity getEntity() {
        return block;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public Collision getCollision() {
        return new ModeSwitcherCollision(this);
    }
    
    public void handleVerticalCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        int displacement = displaceVertically(m.getCollider());
        
        if (displacement >= 0) {
            mario.land();
        } else {
            translate(0, -displacement);
            getEntity().getGraphicElement().translate(0, -displacement);
            
            GraphicEngine.instance().nextMode();
            
            mario.setSpeedY(0);
            
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
}
