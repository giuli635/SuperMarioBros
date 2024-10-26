package entities;

import java.awt.Rectangle;

import graphics.GameGraphicElement;
import colliders.PipeCollider;


public class Pipe extends BaseEntity {
    protected static String PIPE = "pipe";
    protected static String BASE_PIPE = "pipeB";

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
}
