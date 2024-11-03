package colliders.updateables.enemies;

import java.awt.Point;
import java.awt.Rectangle;

import colliders.BaseCollider;
import colliders.updateables.UpdateableEntityCollider;
import collisions.updateables.FireBallCollision;
import collisions.updateables.enemies.EnemyCollision;
import collisions.updateables.enemies.ShellEnemyCollision;
import collisions.updateables.mario.InvulnerableCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.StarMarioCollision;
import entities.updateables.enemies.Enemy;
import entities.updateables.mario.Mario;
import game.GraphicEngine;
import utils.Direction;

public abstract class EnemyCollider extends BaseCollider implements UpdateableEntityCollider {
    protected static int DISPLACEMENT_COEFFICIENT = 0;
    
    public abstract Enemy getEntity();
    
    public EnemyCollider(Rectangle b) {
        super(b);
    }
    
    public void handleHorizontalCollision(InvulnerableCollision m) {
    }
    
    public void handleVerticalCollision(InvulnerableCollision m) {
        Mario mario = m.getCollider().getEntity();
        
        if (calculateCollisionDirection(m) == Direction.DOWN) {
            getEntity().recieveDamage();
            mario.addSpeed(0, Mario.FIXED_BOUNCE_SPEED);
        }
    }

    public void handleHorizontalCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        mario.die();
        mario.modifyPoints(getEntity().pointsToSubtract());
    }

    public void handleVerticalCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        
        if (calculateCollisionDirection(m) == Direction.DOWN) {
            getEntity().recieveDamage();
            mario.modifyPoints(getEntity().pointsToAdd());
            mario.addSpeed(0, Mario.FIXED_BOUNCE_SPEED);
        } else {
            mario.die();
            mario.modifyPoints(getEntity().pointsToSubtract());
        }
    }
    
    public void handleHorizontalCollision(StarMarioCollision m){
        starCollision(m.getCollider().getEntity());
    }

    public void handleVerticalCollision(StarMarioCollision m){
        starCollision(m.getCollider().getEntity());
    }

    private void starCollision(Mario m){
        getEntity().recieveDamage();
        m.modifyPoints(getEntity().pointsToAdd());
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
        if (!s.getCollider().getEntity().getShell() || s.getCollider().getEntity().getSpeedX() == 0) {
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
    
    public void handleHorizontalCollision(FireBallCollision f) {
        getEntity().recieveDamage();
        f.getCollider().getEntity().getMario().modifyPoints(getEntity().pointsToAdd());
        f.getCollider().deactivate();
        f.getCollider().getEntity().unload();
        GraphicEngine.instance().remove(f.getCollider().getEntity().getGraphicElement());
    }
    
    public void handleVerticalCollision(FireBallCollision f) {
        getEntity().recieveDamage();
        f.getCollider().getEntity().getMario().modifyPoints(getEntity().pointsToAdd());
        f.getCollider().deactivate();
        f.getCollider().getEntity().unload();
        GraphicEngine.instance().remove(f.getCollider().getEntity().getGraphicElement());
    }

    protected void bounce(EnemyCollider e) {
        Rectangle collision = getBounds().intersection(e.getBounds());
        
        int displacement = displaceX(collision, DISPLACEMENT_COEFFICIENT);
        getEntity().getGraphicElement().translate(displacement, 0);
        
        getEntity().switchDirection();
        e.getEntity().switchDirection();
    }
    
    public Direction calculateCollisionDirection(MarioCollision m) {
        Direction collisionDirection = Direction.UP;
        
        Rectangle marioBounds = m.getCollider().getBounds();
        Point marioPosition = new Point((int) marioBounds.getCenterX(), (int) marioBounds.getCenterY());
        int outcode = this.getBounds().outcode(marioPosition);
        
        if ((outcode & Rectangle.OUT_BOTTOM) == Rectangle.OUT_BOTTOM
        && m.getCollider().getEntity().getSpeedY() < -Mario.GRAVITY) {
            collisionDirection = Direction.DOWN;
        }
        
        return collisionDirection;
    }
}
