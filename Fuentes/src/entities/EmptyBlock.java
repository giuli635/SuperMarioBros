package entities;

import java.awt.Rectangle;

import colliders.invisibles.EmptyBlockCollider;
import game.LevelReader;
import graphics.GameGraphicElement;

public class EmptyBlock implements Entity {
    public final static int POINTS = -15; 

    protected EmptyBlockCollider collider;
    protected GameGraphicElement graphicElement;

    public EmptyBlock() {
        collider = new EmptyBlockCollider(this, new Rectangle(LevelReader.CHUNK, LevelReader.CHUNK));
        graphicElement = null;
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public EmptyBlockCollider getCollider() {
        return collider;
    }
}
