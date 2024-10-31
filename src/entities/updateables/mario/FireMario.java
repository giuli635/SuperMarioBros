package entities.updateables.mario;

import java.awt.Rectangle;

import colliders.updateables.mario.FireMarioCollider;
import colliders.updateables.mario.MarioCollider;
import entities.updateables.mario.actions.Crouch;
import entities.updateables.mario.actions.ThrowFireBall;

public class FireMario extends BaseMarioState {
    protected static final String FIRE_MARIO_SPRITES = "fireMario";
    protected Crouch crouch;
    protected ThrowFireBall fireBallThrower;

    public FireMario(Mario m) {
        super(m);
        newSpritesFolder = FIRE_MARIO_SPRITES;
    }

    @Override
    public void setState() {
        MarioCollider newCollider = new FireMarioCollider(mario, new Rectangle());

        swapState(newCollider);
        
        crouch = new Crouch();
        fireBallThrower = new ThrowFireBall();
        mario.addAction(crouch);
        mario.addAction(fireBallThrower);
    }

    @Override
    public void removeState() {
        revertState();
        mario.removeAction(crouch);
        mario.removeAction(fireBallThrower);
    }
}
