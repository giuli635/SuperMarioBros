package entities.mario;

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
        GameGraphicElement graphicElement = mario.getGraphicElement();
        previousStateSpriteFolder = graphicElement.getFolder();
        previousCollider = mario.getCollider();
        graphicElement.setFolder("superMario");
        mario.setCollider(new SuperMarioCollider(mario, previousCollider.getBounds()));

        crouch = new Crouch();
        mario.addAction(crouch);
    }

    @Override
    public void removeState() {
        GameGraphicElement graphicElement = mario.getGraphicElement();
        graphicElement.setFolder(previousStateSpriteFolder);

        mario.setCollider(previousCollider);
        mario.removeAction(crouch);
    }
}
