package entities.mario;

import java.awt.Point;

import colliders.MarioCollider;
import colliders.SuperMarioCollider;
import entities.mario.actions.Crouch;
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
        mario.setCollider(new SuperMarioCollider(mario, previousCollider.getBounds()));
        GameGraphicElement graphicElement = mario.getGraphicElement();
        previousStateSpriteFolder = graphicElement.getFolder();
        graphicElement.setFolder("superMario");

        crouch = new Crouch();
        mario.addAction(crouch);
    }

    @Override
    public void removeState() {
        GameGraphicElement graphicElement = mario.getGraphicElement();
        graphicElement.setFolder(previousStateSpriteFolder);

        Point currentColliderPosition = mario.getCollider().getPosition();
        previousCollider.setPosition((int) currentColliderPosition.getX(), (int) currentColliderPosition.getY());
        mario.setCollider(previousCollider);
        mario.removeAction(crouch);
    }
}
