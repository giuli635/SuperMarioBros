package colliders;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import colliders.solids.SolidCollider;
import collisions.Collision;
import collisions.LanguageSwitcherCollision;
import collisions.updateables.mario.MarioCollision;
import entities.solids.QuestionBlock;
import entities.updateables.mario.Mario;
import game.LanguageConfiguration;
import utils.Axis;

public class LanguageSwitcherCollider extends SolidCollider{
    protected QuestionBlock block;

    public LanguageSwitcherCollider(QuestionBlock e, Rectangle b) {
        super(b);
        block = e;
    }

    @Override
    public QuestionBlock getEntity() {
        return block;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public LanguageSwitcherCollision getCollision() {
        return new LanguageSwitcherCollision(this);
    }
    
    public void handleVerticalCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        int displacement = displaceVertically(m.getCollider());
        
        if (displacement >= 0) {
            mario.land();
        } else {
            translate(0, -displacement);
            getEntity().getGraphicElement().translate(0, -displacement);
            LanguageConfiguration.instance().nextLanguage();
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
