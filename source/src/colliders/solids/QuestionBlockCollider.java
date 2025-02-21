package colliders.solids;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import colliders.updateables.UpdateableEntityCollider;
import collisions.VisitorCollision;
import collisions.solids.QuestionBlockCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import utils.Axis;
import entities.solids.QuestionBlock;
import entities.updateables.mario.Mario;
import entities.updateables.powerups.FireFlower;
import entities.updateables.powerups.PowerUp;
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
    public void recieveCollision(VisitorCollision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public QuestionBlockCollision getCollision() {
        return new QuestionBlockCollision(this);
    }

    public void handleVerticalCollision(MarioCollision m) {
        marioVerticalCollision(new SuperMushroom(), m);
    }

    public void handleVerticalCollision(SuperMarioCollision m) {
        marioVerticalCollision(new FireFlower(), m);
    }

    protected void marioVerticalCollision(PowerUp p, MarioCollision m) {
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
                questionBlock.interaction(p);
                
                fallBackIntoPlace(displacement);
            }
        }
    }

    protected void fallBackIntoPlace(int displacement) {
        Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run(){
                    setColliding(true);
                    translate(0, displacement);
                    getEntity().getGraphicElement().translate(0, displacement);
                    setColliding(false);;
                }
            };

        timer.schedule(task,300);
    }
}
