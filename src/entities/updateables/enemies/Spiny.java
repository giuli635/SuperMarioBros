package entities.updateables.enemies;

import java.awt.Rectangle;

import colliders.updateables.enemies.SpinyCollider;
import graphics.GameGraphicElement;

public class Spiny extends BaseEnemy {
    protected static String SPRITES_FOLDER = "spiny";
    protected int speedX;
    public final static int POINTS = 60;

    protected SpinyCollider collider;
    protected GameGraphicElement graphicElement;

    public Spiny() {
        super();
        collider = new SpinyCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public void recieveDamage() {
        die(SPRITES_FOLDER);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public SpinyCollider getCollider() {
        return collider;
    }

    @Override
    public int pointsToAdd() {
        return POINTS;
    }

    @Override
    public int pointsToSubtract() {
        return -(POINTS/2);
    }
}
