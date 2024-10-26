package entities.mario;

import colliders.Collider;
import graphics.GraphicElement;

public class SuperMario implements MarioState {
    protected static final String SUPER_MARIO_SPRITES = "superMario";

    @Override
    public void setState(Mario m) {
        GraphicElement graphicElement = m.getGraphicElement();
        Collider collider = m.getCollider();
        graphicElement.setFolder("superMario");
        
        collider.setSize(32, 64);
        m.getCollider().translate(0, -32);
    }

    @Override
    public void removeState(Mario m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeState'");
    }
}
