package colliders.updateables.enemies;

import java.awt.Rectangle;

import collisions.updateables.FireBallCollision;
import collisions.updateables.enemies.EnemyCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.StarMarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import entities.updateables.enemies.ShellEnemy;
import entities.updateables.mario.Mario;
import entities.updateables.mario.states.InvulnerableState;
import utils.Direction;

public abstract class ShellEnemyCollider extends EnemyCollider{
    public abstract ShellEnemy getEntity();

    public ShellEnemyCollider(Rectangle b) {
        super(b);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        Mario mario = m.getCollider().getEntity();
        if (!getEntity().getShell()) {
            mario.die();
            mario.modifyPoints(getEntity().pointsToSubtract());
        } else if(getEntity().getSpeedX() == 0) {
            int displacement = m.getCollider().displaceX(collision, 3);
            mario.getGraphicElement().translate(displacement, 0);
            getEntity().setSpeedX((int) -Math.signum(displacement) * 6);
        } else {
            mario.die();
            mario.modifyPoints(getEntity().pointsToSubtract());
        }
    }

    public void handleVerticalCollision(MarioCollision m) {
        Direction collisionDirection = m.getCollider().getVelocity().getYComponent() > 0 ? Direction.UP : Direction.DOWN;
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        Mario mario = m.getCollider().getEntity();
        if(collisionDirection == Direction.DOWN) {
            getEntity().recieveDamage();
            mario.modifyPoints(getEntity().pointsToAdd()/2);
            int displacement = m.getCollider().displaceY(collision, 3);
            mario.getGraphicElement().translate(0, displacement);
            mario.addSpeed(0, Mario.FIXED_BOUNCE_SPEED);
        } else {
            if (!getEntity().getShell()) {
                mario.die();
                mario.modifyPoints(getEntity().pointsToSubtract());
            }
        }
    }

    public void handleHorizontalCollision(SuperMarioCollision m) {
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        Mario mario = m.getCollider().getEntity();
        
        if (!getEntity().getShell()) {
            mario.removeState(m.getCollider().getAssociatedState());
            mario.setState(new InvulnerableState(mario));
        } else if(getEntity().getSpeedX() == 0) {
            int displacement = m.getCollider().displaceX(collision, 3);
            mario.getGraphicElement().translate(displacement, 0);
            getEntity().setSpeedX((int) -Math.signum(displacement) * 6);
            mario.removeState(m.getCollider().getAssociatedState());
            mario.setState(new InvulnerableState(mario)); 
        } else {
            mario.removeState(m.getCollider().getAssociatedState());
            mario.setState(new InvulnerableState(mario));
        }
    }

    public void handleVerticalCollision(SuperMarioCollision m) {
        Direction collisionDirection = calculateCollisionDirection(m);
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        Mario mario = m.getCollider().getEntity();
        
        if(collisionDirection == Direction.DOWN) {
            getEntity().recieveDamage();
            mario.modifyPoints(getEntity().pointsToAdd()/2);
            int displacement = m.getCollider().displaceY(collision, 3);
            mario.getGraphicElement().translate(0, displacement);
            mario.addSpeed(0, Mario.FIXED_BOUNCE_SPEED);
        } else {
            if (!getEntity().getShell()) {
                mario.removeState(m.getCollider().getAssociatedState());
                mario.setState(new InvulnerableState(mario));
            }
            else{
                mario.removeState(m.getCollider().getAssociatedState());
                mario.setState(new InvulnerableState(mario));
            }
        }
    }

    public void handleHorizontalCollision(StarMarioCollision m){
        getEntity().recieveDamage();
        m.getCollider().getEntity().modifyPoints(getEntity().pointsToAdd()/2);
    }

    public void handleVerticalCollision(StarMarioCollision m){
        getEntity().recieveDamage();
        m.getCollider().getEntity().modifyPoints(getEntity().pointsToAdd()/2);
    }

    @Override
    public void handleHorizontalCollision(FireBallCollision f) {
        super.handleHorizontalCollision(f);
        getEntity().recieveDamage();
    }

    @Override
    public void handleVerticalCollision(FireBallCollision f) {
        super.handleVerticalCollision(f);
        getEntity().recieveDamage();
    }

    @Override
    public void handleHorizontalCollision(EnemyCollision e) {
    }
    
    @Override
    public void handleVerticalCollision(EnemyCollision e) {
    }
}
