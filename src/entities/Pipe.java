package entities;

import java.awt.Rectangle;

import graphics.GameGraphicElement;
import colliders.PipeCollider;


public class Pipe implements Entity {
    protected static String FOLDER_PATH = "pipe";
    protected static String TOP_PIPE = "topPipe";
    protected static String BASE_PIPE = "basePipe";

    protected PipeCollider collider;
    protected GameGraphicElement graphicElement;

    public Pipe() {
        collider = new PipeCollider(this, new Rectangle(64, 32));
        graphicElement = new GameGraphicElement(this, FOLDER_PATH);
        graphicElement.setSprite(TOP_PIPE);
        
    }

    public Pipe(int i) {
        collider = new PipeCollider(this, new Rectangle(64, 32));
        graphicElement = new GameGraphicElement(this, FOLDER_PATH);
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
