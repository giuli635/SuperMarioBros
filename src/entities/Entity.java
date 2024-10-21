package entities;
import colliders.Collider;
import graphics.GraphicElement;

public interface Entity {
    public GraphicElement getGraphicElement();
    public Collider getCollider();
}
