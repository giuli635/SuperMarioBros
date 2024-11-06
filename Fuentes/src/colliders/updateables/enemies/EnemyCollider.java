package colliders.updateables.enemies;

import java.awt.Point;
import java.awt.Rectangle;

import colliders.BaseCollider;
import colliders.updateables.MovableEntityCollider;
import collisions.updateables.FireBallCollision;
import collisions.updateables.enemies.EnemyCollision;
import collisions.updateables.enemies.ShellEnemyCollision;
import collisions.updateables.mario.InvulnerableCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.StarMarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import entities.updateables.enemies.BaseEnemy;
import entities.updateables.enemies.Enemy;
import entities.updateables.mario.Mario;
import entities.updateables.mario.states.Invulnerable;
import game.SingletonGame;
import game.SingletonSoundManager;
import utils.Direction;

public abstract class EnemyCollider extends BaseCollider implements MovableEntityCollider {
    protected static int DISPLACEMENT_COEFFICIENT = 0;
    
    public abstract Enemy getEntity();
    
    public EnemyCollider(Rectangle b) {
        super(b);
    }

    protected void kill(Mario mario) {
        mario.die();
        mario.modifyPoints(getEntity().pointsToSubtract());
    }

    protected void getKilled(Mario mario, String sound) {
        getEntity().recieveDamage();
        SingletonSoundManager.instance().playSound(sound);
        mario.modifyPoints(getEntity().pointsToAdd());
        Rectangle collision = getBounds().intersection(mario.getCollider().getBounds());
    
        int displacement = mario.getCollider().displaceY(collision, 0);
        mario.getGraphicElement().translate(0, displacement);
    }

    public Direction calculateCollisionDirection(MarioCollision m) {
        Direction collisionDirection = Direction.UP;
        
        Rectangle marioBounds = m.getCollider().getBounds();
        Point marioPosition = new Point(
            (int) marioBounds.getCenterX(),
            (int) marioBounds.getCenterY()
        );

        int outcode = this.getBounds().outcode(marioPosition);
        boolean isOnTop = (outcode & Rectangle.OUT_BOTTOM) == Rectangle.OUT_BOTTOM;
        boolean marioIsFalling = m.getCollider().getEntity().getSpeedY() < -(Mario.GRAVITY * 2);
        
        if (isOnTop && marioIsFalling) {
            collisionDirection = Direction.DOWN;
        }
        
        return collisionDirection;
    }

    public void handleHorizontalCollision(MarioCollision m) {
        kill(m.getCollider().getEntity());
    }

    public void handleVerticalCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        if (calculateCollisionDirection(m) == Direction.DOWN) {
            getKilled(mario, BaseEnemy.STOMP_SOUND);
            mario.addSpeed(0, Mario.FIXED_BOUNCE_SPEED);
        } else {
            kill(mario);
        }
    }
    
    public void handleHorizontalCollision(SuperMarioCollision m) {
        Mario mario = m.getCollider().getEntity();

        mario.removeState(m.getCollider().getAssociatedState());
        mario.setState(new Invulnerable(mario));
    }

    public void handleVerticalCollision(SuperMarioCollision m){
        Mario mario = m.getCollider().getEntity();

        if (calculateCollisionDirection(m) == Direction.DOWN) {
            getKilled(mario, BaseEnemy.STOMP_SOUND);
            mario.addSpeed(0, Mario.FIXED_BOUNCE_SPEED);
        } else {
            mario.removeState(m.getCollider().getAssociatedState());
            mario.setState(new Invulnerable(mario));
        }
    }
    
    public void handleVerticalCollision(InvulnerableCollision m) {
        Mario mario = m.getCollider().getEntity();
        if (calculateCollisionDirection(m) == Direction.DOWN) {
            getKilled(mario, BaseEnemy.STOMP_SOUND);
            mario.addSpeed(0, Mario.FIXED_BOUNCE_SPEED);
        }
    }

    public void handleHorizontalCollision(InvulnerableCollision m) {
    }

    public void handleHorizontalCollision(StarMarioCollision m){
        getKilled(m.getCollider().getEntity(), BaseEnemy.DIE_FIRE_SOUND);
    }

    public void handleVerticalCollision(StarMarioCollision m){
        getKilled(m.getCollider().getEntity(), BaseEnemy.DIE_FIRE_SOUND);
    }

    public void handleHorizontalCollision(FireBallCollision f) {
        getKilled(f.getCollider().getEntity().getMario(), BaseEnemy.DIE_FIRE_SOUND);
        f.getCollider().getEntity().destroy();
    }
    
    public void handleVerticalCollision(FireBallCollision f) {
        getKilled(f.getCollider().getEntity().getMario(), BaseEnemy.DIE_FIRE_SOUND);
        f.getCollider().getEntity().destroy();
    }
    
    public void handleVerticalCollision(EnemyCollision e) {
        Rectangle collision = getBounds().intersection(e.getCollider().getBounds());
        Enemy enemy = e.getCollider().getEntity();
        
        int displacement = e.getCollider().displaceY(collision, DISPLACEMENT_COEFFICIENT);
        enemy.getGraphicElement().translate(0, displacement);
        displacement = e.getCollider().displaceX(collision, DISPLACEMENT_COEFFICIENT);
        enemy.getGraphicElement().translate(displacement, 0);
    }
    
    public void handleVerticalCollision(ShellEnemyCollision s) {
        if (s.getCollider().getEntity().getShell()) {
            getEntity().recieveDamage();
            SingletonSoundManager.instance().playSound(BaseEnemy.DIE_FIRE_SOUND);
        } else {
            handleVerticalCollision((EnemyCollision) s);
        }
    }
    
    protected void bounce(EnemyCollider e) {
        Rectangle collision = getBounds().intersection(e.getBounds());
        
        int displacement = displaceX(collision, DISPLACEMENT_COEFFICIENT);
        getEntity().getGraphicElement().translate(displacement, 0);
        
        getEntity().switchDirection();
        e.getEntity().switchDirection();
    }

    public void handleHorizontalCollision(ShellEnemyCollision s) {
        if (!s.getCollider().getEntity().getShell() || s.getCollider().getEntity().getSpeedX() == 0) {
            bounce(s.getCollider());
        } else {
            getEntity().recieveDamage();
            SingletonSoundManager.instance().playSound(BaseEnemy.DIE_FIRE_SOUND);
        }
    }
    
    public void handleHorizontalCollision(EnemyCollision e) {
        SingletonGame.instance().setDebugging(true);
        bounce(e.getCollider());
    }
}
