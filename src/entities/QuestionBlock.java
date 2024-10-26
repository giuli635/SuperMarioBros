package entities;

import java.awt.Rectangle;

import graphics.GameGraphicElement;
import colliders.QuestionBlockCollider;

public class QuestionBlock extends BaseEntity {
    protected static String SPRITES_FOLDER = "questionBlock";

    public QuestionBlock() {
        collider = new QuestionBlockCollider(this, new Rectangle(32, 32));
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
    }
}
