package entities;

import java.awt.Rectangle;

import graphics.GameGraphicElement;
import colliders.PipeCollider;


public class Pipe implements Entity {
    protected static String PIPE = "pipe";
    protected static String BASE_PIPE = "pipeB";

    protected PipeCollider collider;
    protected GameGraphicElement graphicElement;

    public Pipe() {
        collider = new PipeCollider(this, new Rectangle(64, 32));
        graphicElement = new GameGraphicElement(this, PIPE);
        graphicElement.setSprite(PIPE);
        
    }

    public Pipe(int i) {
        collider = new PipeCollider(this, new Rectangle(64, 32));
        graphicElement = new GameGraphicElement(this, BASE_PIPE);
        graphicElement.setSprite(BASE_PIPE);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public PipeCollider getCollider() {
        return collider;
    }
}
