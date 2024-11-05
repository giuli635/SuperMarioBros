package entities.updateables.mario.states;

import java.awt.Rectangle;

import colliders.updateables.mario.FireMarioCollider;
import colliders.updateables.mario.MarioCollider;
import entities.updateables.mario.Mario;
import entities.updateables.mario.actions.Crouch;
import entities.updateables.mario.actions.ThrowFireBall;

public class FireMario extends SuperMario {
    protected static final String FIRE_MARIO_SPRITES = "fireMario";
    protected static final int PRIORITY = 0;

    protected ThrowFireBall fireBallThrower;

    public FireMario(Mario m) {
        super(m);
        newSpritesFolder = FIRE_MARIO_SPRITES;
        priority = PRIORITY;
    }

    @Override
    public void setState() {
        MarioCollider newCollider = new FireMarioCollider(mario, new Rectangle(), this);

        previousCollider = mario.setCollider(newCollider);
        swapSprites();
        
        crouch = new Crouch(mario);
        fireBallThrower = new ThrowFireBall(mario);

        mario.addAction(crouch);
        mario.addAction(fireBallThrower);
    }

    @Override
    public void removeState() {
        crouch.unCrouch(mario);
        mario.setCollider(previousCollider);
        revertSprites();
        mario.removeAction(crouch);
        mario.removeAction(fireBallThrower);
    }
}
