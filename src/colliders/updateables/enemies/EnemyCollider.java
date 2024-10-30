package colliders.updateables.enemies;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import colliders.BaseCollider;
import colliders.updateables.UpdateableEntityCollider;
import collisions.updateables.enemies.EnemyCollision;
import collisions.updateables.enemies.ShellEnemyCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import entities.updateables.enemies.Enemy;
import entities.updateables.mario.Mario;
import utils.Direction;

public abstract class EnemyCollider extends BaseCollider implements UpdateableEntityCollider {
    protected static int DISPLACEMENT_COEFFICIENT = 0;
    public abstract Enemy getEntity();

    public EnemyCollider(Rectangle b) {
        super(b);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        m.getCollider().getEntity().die();
    }

    public void handleHorizontalCollision(SuperMarioCollision m) {
        m.getCollider().getEntity().removeState();
        m.getCollider().deactivate();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run(){
                m.getCollider().activate();
                //m.getCollider().setPosition((int)m.getCollider().getPosition().getX(),(int) m.getCollider().getPosition().getX() );
            }
        }; 
        timer.schedule(task,3000);
    }
    

    public void handleVerticalCollision(MarioCollision m) {
        Direction collisionDirection = Direction.verticalDirectionFromSign(
            (int) m.getCollider().getVelocity().getYComponent()
        );
        Mario mario = m.getCollider().getEntity();

        if(collisionDirection == Direction.DOWN) {
            getEntity().recieveDamage();
            mario.addSpeed(0, Mario.FIXED_BOUNCE_SPEED);
        } else {
            //mario.die();
            m.getCollider().getEntity().removeState();
            m.getCollider().deactivate();
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run(){
                  m.getCollider().activate();
                //m.getCollider().setPosition((int)m.getCollider().getPosition().getX(),(int) m.getCollider().getPosition().getX() );
                }
        }; 
        timer.schedule(task,3000);
        }

    }
    

    public void handleVerticalCollision(ShellEnemyCollision s) {
        if (!s.getCollider().getEntity().getShell() || s.getCollider().getEntity().getSpeedX() == 0) {
            Rectangle collision = getBounds().intersection(s.getCollider().getBounds());

            int displacement = s.getCollider().displaceY(collision, DISPLACEMENT_COEFFICIENT);
            s.getCollider().getEntity().getGraphicElement().translate(0, displacement);
        } else {
            getEntity().recieveDamage();
        }
    }

    public void handleHorizontalCollision(ShellEnemyCollision s) {
        if(!s.getCollider().getEntity().getShell() || s.getCollider().getEntity().getSpeedX() == 0) {
            bounce(s.getCollider());
        } else {
            getEntity().recieveDamage();
        }
    }

    public void handleVerticalCollision(EnemyCollision e) {
        Rectangle collision = getBounds().intersection(e.getCollider().getBounds());
        Enemy enemy = e.getCollider().getEntity();

        int displacement = e.getCollider().displaceY(collision, DISPLACEMENT_COEFFICIENT);
        enemy.getGraphicElement().translate(0, displacement);
        displacement = e.getCollider().displaceX(collision, DISPLACEMENT_COEFFICIENT);
        enemy.getGraphicElement().translate(displacement, 0);
    }

    public void handleHorizontalCollision(EnemyCollision e) {
        bounce(e.getCollider());
    }

    protected void bounce(EnemyCollider e) {
        Rectangle collision = getBounds().intersection(e.getBounds());

        int displacement = e.displaceX(collision, DISPLACEMENT_COEFFICIENT);
        e.getEntity().getGraphicElement().translate(displacement, 0);

        e.getEntity().switchDirection();
        getEntity().switchDirection();
    }
}
