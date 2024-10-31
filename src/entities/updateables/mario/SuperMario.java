package entities.updateables.mario;

import java.awt.Point;

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
        MarioCollider newCollider = new SuperMarioCollider(mario, previousCollider.getBounds());
        mario.setCollider(newCollider);

        GameGraphicElement graphicElement = mario.getGraphicElement();
        previousStateSpriteFolder = graphicElement.getFolder();
        mario.setSpritesFolder("superMario");

        crouch = new Crouch();
        mario.addAction(crouch);
    }

    @Override
    public void removeState() {
        mario.setSpritesFolder(previousStateSpriteFolder);

        Point currentColliderPosition = mario.getCollider().getPosition();
        previousCollider.setPosition((int) currentColliderPosition.getX(), (int) currentColliderPosition.getY());
        mario.setCollider(new InvulnerableCollider(mario));
        mario.setCollider(previousCollider);
        mario.addAction(new DisappearSprite());
        mario.removeAction(crouch);
    }
}
