package entities.solids;

import java.awt.Rectangle;

import colliders.solids.PipeCollider;
import entities.Body;
import graphics.GameGraphicElement;

public class Pipe extends Body {
    protected static String FOLDER_PATH = "pipe";
    protected static String TOP_PIPE = "topPipe";
    protected static String BASE_PIPE = "basePipe";

    protected PipeCollider collider;
    protected GameGraphicElement graphicElement;

    public Pipe(char c) {
        collider = new PipeCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, FOLDER_PATH);
        if (c == 'P')
            setSprite(TOP_PIPE);
        else {
            setSprite(BASE_PIPE);
        }
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
