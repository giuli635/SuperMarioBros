package entities;

import colliders.Collider;
import graphics.GraphicElement;

public abstract class BaseEntity implements Entity {
    protected Collider collider;
    protected GraphicElement graphicElement;

    public Collider getCollider() {
        return collider;
    }

    public GraphicElement getGraphicElement() {
        return graphicElement;
    }
}
