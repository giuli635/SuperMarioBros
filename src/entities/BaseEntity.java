package entities;

import colliders.Collider;
import graphics.GameGraphicElement;

public abstract class BaseEntity implements Entity {
    protected Collider collider;
    protected GameGraphicElement graphicElement;

    public Collider getCollider() {
        return collider;
    }

    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }
}
