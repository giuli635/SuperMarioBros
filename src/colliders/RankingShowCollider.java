package colliders;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import colliders.solids.SolidCollider;
import colliders.updateables.UpdateableEntityCollider;
import collisions.Collision;
import collisions.RankingShowCollision;
import collisions.updateables.mario.MarioCollision;
import entities.ConfigurationBlock;
import entities.updateables.mario.Mario;
import game.Game;
import utils.Axis;

public class RankingShowCollider extends SolidCollider implements UpdateableEntityCollider {
    protected ConfigurationBlock block;

    public RankingShowCollider(ConfigurationBlock q, Rectangle b) {
        super(b);
        block = q;
    }

    @Override
    public ConfigurationBlock getEntity() {
        return block;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public Collision getCollision() {
        return new RankingShowCollision(this);
    }
    
    public void handleVerticalCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        int displacement = displaceVertically(m.getCollider());
        
        if (displacement >= 0) {
            mario.land();
        } else {
            translate(0, -displacement);
            getEntity().getGraphicElement().translate(0, -displacement);
            
            Game.instance().showRanking(); //no me gusta tanto hacerlo asi -.-
                                          
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
