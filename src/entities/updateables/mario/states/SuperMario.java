package entities.updateables.mario.states;

import java.awt.Rectangle;

import colliders.updateables.mario.MarioCollider;
import colliders.updateables.mario.SuperMarioCollider;
import entities.updateables.mario.Mario;
import entities.updateables.mario.actions.Crouch;

public class SuperMario extends BaseMarioState {
    protected static final String SUPER_MARIO_SPRITES = "superMario";
    protected static final int PRIORITY = 0;
    protected Crouch crouch;

    public SuperMario(Mario m) {
        super(m);
        newSpritesFolder = SUPER_MARIO_SPRITES;
        priority = PRIORITY;
    }

    @Override
    public void setState() {
        MarioCollider newCollider = new SuperMarioCollider(mario, new Rectangle(), this);

        previousCollider = mario.setCollider(newCollider);
        swapSprites();
        
        crouch = new Crouch();
        mario.addAction(crouch);
    }

    @Override
    public void removeState() {
        crouch.unCrouch(mario);
        mario.setCollider(previousCollider);
        revertSprites();
        mario.removeAction(crouch);
    }
}
