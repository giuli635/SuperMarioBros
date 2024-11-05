package entities;
import colliders.Collider;
import graphics.GameGraphicElement;

public interface Entity {
    public GameGraphicElement getGraphicElement();
    public Collider getCollider();
}
