package entities.updateables.mario.states;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Map;

import colliders.updateables.mario.FireMarioCollider;
import colliders.updateables.mario.MarioCollider;
import entities.updateables.mario.Mario;
import entities.updateables.mario.actions.Crouch;
import entities.updateables.mario.actions.ThrowFireBall;

public class FireMario extends SuperMario {
    protected static final String FIRE_MARIO_SPRITES = "fireMario";
    protected static final int PRIORITY = 0;
    protected static final Map<Color, Color> INITIAL_COLOR_STAR_MARIO = initStarColor();
    protected Map<Color, Color> previousStarColors;

    protected static Map<Color, Color> initStarColor() {
        Map<Color, Color> colorMapping = Map.of(
            new Color(181, 49, 32, 255), new Color(234, 158, 34, 255),
            new Color(247, 216, 165, 255), new Color(12, 147, 0, 255),
            new Color(234, 158, 34, 255), new Color(255, 254, 255, 255)
        );
        
        return colorMapping;
    }

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
        
        crouch = new Crouch();
        fireBallThrower = new ThrowFireBall();

        previousStarColors = mario.getInitialColorStarMario();
        mario.setInitialColorStarMario(INITIAL_COLOR_STAR_MARIO);
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
        mario.setInitialColorStarMario(previousStarColors);
    }
}
