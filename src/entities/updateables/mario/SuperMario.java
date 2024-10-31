package entities.updateables.mario;

import java.awt.Rectangle;

import colliders.updateables.mario.MarioCollider;
import colliders.updateables.mario.SuperMarioCollider;
import entities.updateables.mario.actions.Crouch;

public class SuperMario extends BaseMarioState {
    protected static final String SUPER_MARIO_SPRITES = "superMario";
    protected Crouch crouch;

    public SuperMario(Mario m) {
        super(m);
        newSpritesFolder = SUPER_MARIO_SPRITES;
    }

    @Override
    public void setState() {
        MarioCollider newCollider = new SuperMarioCollider(mario, new Rectangle());

        swapCollider(newCollider);
        
        crouch = new Crouch();
        mario.addAction(crouch);
    }

    @Override
    public void removeState() {
        revertState();
        mario.removeAction(crouch);
    }
}
