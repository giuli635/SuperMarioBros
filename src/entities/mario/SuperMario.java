package entities.mario;

import colliders.Collider;
import graphics.GameGraphicElement;

public class SuperMario implements MarioState {
    protected static final String SUPER_MARIO_SPRITES = "superMario";
    protected Croucher croucher;

    @Override
    public void setFunctionality(Mario m) {
        GameGraphicElement graphicElement = m.getGraphicElement();
        Collider collider = m.getCollider();
        graphicElement.setFolder("superMario");
        
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );

        croucher = new Croucher(m);
        croucher.load();
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
