package colliders.updateables.enemies;

import java.awt.Rectangle;

import collisions.updateables.FireBallCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.StarMarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import entities.updateables.enemies.ShellEnemy;
import entities.updateables.mario.Mario;
import entities.updateables.mario.states.InvulnerableState;

public abstract class ShellEnemyCollider extends EnemyCollider {
    public abstract ShellEnemy getEntity();

    public ShellEnemyCollider(Rectangle b) {
        super(b);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        Mario mario = m.getCollider().getEntity();

        if (getEntity().getSpeedX() == 0 && getEntity().getShell()) {
            int displacement = m.getCollider().displaceX(collision, 0);
            mario.getGraphicElement().translate(displacement, 0);

            getEntity().setSpeedX((int) -Math.signum(displacement) * ShellEnemy.SHELL_SPEED);
        } else {
            kill(mario);
        }
    }

    public void handleHorizontalCollision(SuperMarioCollision m) {
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        Mario mario = m.getCollider().getEntity();
        
        if(getEntity().getSpeedX() == 0 && getEntity().getShell()) {
            int displacement = m.getCollider().displaceX(collision, 0);
            mario.getGraphicElement().translate(displacement, 0);

            getEntity().setSpeedX((int) -Math.signum(displacement) * ShellEnemy.SHELL_SPEED);
        } else {
            mario.removeState(m.getCollider().getAssociatedState());
            mario.setState(new InvulnerableState(mario));
        }
    }

    public void handleHorizontalCollision(StarMarioCollision m){
        super.handleHorizontalCollision(m);
        getEntity().recieveDamage();
    }

    public void handleVerticalCollision(StarMarioCollision m){
        super.handleHorizontalCollision(m);
        getEntity().recieveDamage();
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
}
