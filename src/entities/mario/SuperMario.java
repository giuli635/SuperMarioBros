package entities.mario;

import colliders.Collider;
import entities.mario.actions.Crouch;
import graphics.GameGraphicElement;

public class SuperMario implements MarioState {
    protected static final String SUPER_MARIO_SPRITES = "superMario";

    @Override
    public void setFunctionality(Mario m) {
        GameGraphicElement graphicElement = m.getGraphicElement();
        Collider collider = m.getCollider();
        graphicElement.setFolder("superMario");
        m.addAction(new Crouch());
        
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public void setCollisions(Mario m) {
        m.getCollider();
    }

    @Override
    public void removeState(Mario m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeState'");
    }
}
