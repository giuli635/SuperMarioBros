package entities;

import java.awt.Rectangle;

import graphics.GameGraphicElement;
import colliders.QuestionBlockCollider;

public class QuestionBlock implements Entity {
    protected static String SPRITES_FOLDER = "questionBlock";

    protected QuestionBlockCollider collider;
    protected GameGraphicElement graphicElement;

    public QuestionBlock() {
        collider = new QuestionBlockCollider(this, new Rectangle(32, 32));
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public QuestionBlockCollider getCollider() {
        return collider;
    }
}
