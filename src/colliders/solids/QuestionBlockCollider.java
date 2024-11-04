package colliders.solids;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import colliders.updateables.UpdateableEntityCollider;
import collisions.Collision;
import collisions.solids.QuestionBlockCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import utils.Axis;
import entities.solids.QuestionBlock;
import entities.updateables.mario.Mario;
import entities.updateables.powerups.FireFlower;
import entities.updateables.powerups.SuperMushroom;

public class QuestionBlockCollider extends SolidCollider implements UpdateableEntityCollider {
    protected QuestionBlock questionBlock;

    public QuestionBlockCollider(QuestionBlock e, Rectangle b) {
        super(b);
        questionBlock = e;
    }

    public QuestionBlock getEntity() {
        return questionBlock;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public QuestionBlockCollision getCollision() {
        return new QuestionBlockCollision(this);
    }

    public void handleVerticalCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        int displacement = displaceVertically(m.getCollider());
        
        if (!questionBlock.getActive()) {
            if (displacement >= 0) {
                mario.land();
            }
        } else {
            if (displacement >= 0) {
                mario.land();
            } else {
                mario.setSpeedY(0);
                translate(0, -displacement);
                getEntity().getGraphicElement().translate(0, -displacement);
                mario.setSpeedY(0);
                questionBlock.interaction(new SuperMushroom());
                
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    public void run(){
                        translate(0, displacement);
                        getEntity().getGraphicElement().translate(0, displacement);
                    }
                };

                timer.schedule(task, 300);
            }
        }
    }

    public void handleVerticalCollision(SuperMarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        int displacement = displaceVertically(m.getCollider());
        
        if (!questionBlock.getActive()) {
            if (displacement >= 0) {
                mario.land();
            }
        } else {
            if (displacement >= 0) {
                mario.land();
            } else {
                translate(0, -displacement);
                getEntity().getGraphicElement().translate(0, -displacement);
                mario.setSpeedY(0);
                questionBlock.interaction(new FireFlower());
                
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
}
