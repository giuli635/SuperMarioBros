package entities.updateables.mario;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import colliders.updateables.mario.InvulnerableCollider;
import colliders.updateables.mario.MarioCollider;
import colliders.updateables.mario.SuperMarioCollider;
import entities.updateables.mario.actions.Crouch;
import entities.updateables.mario.actions.DisappearSprite;
import graphics.GameGraphicElement;

public class SuperMario extends BaseMarioState {
    protected static final String SUPER_MARIO_SPRITES = "superMario";
    protected String previousStateSpriteFolder;
    protected MarioCollider previousCollider;
    protected Crouch crouch;

    public SuperMario(Mario m) {
        super(m);
    }

    @Override
    public void setState() {
        previousCollider = mario.getCollider();
        MarioCollider newCollider = new SuperMarioCollider(mario, new Rectangle());
        newCollider.copy(previousCollider);
        previousCollider.track(newCollider);
        mario.setUnderlyingCollider(newCollider);

        GameGraphicElement graphicElement = mario.getGraphicElement();
        previousStateSpriteFolder = graphicElement.getFolder();
        mario.setSpritesFolder("superMario");

        crouch = new Crouch();
        mario.addAction(crouch);
    }

    @Override
    public void removeState() {
        mario.setSpritesFolder(previousStateSpriteFolder);

        MarioCollider newCollider = new InvulnerableCollider(mario);
        newCollider.copy(mario.getCollider());
        mario.setCollider(newCollider);
        mario.setUnderlyingCollider(previousCollider);
        mario.setSpritesFolder("mario");
        mario.addAction(new DisappearSprite());

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                MarioCollider collider = mario.getCollider().getBaseCollider();
                collider.copy(mario.getCollider());
                mario.setCollider(collider);
                mario.removeAction(new DisappearSprite());
            }
        };

        timer.schedule(task, 3000);

        mario.removeAction(crouch);
    }
}
