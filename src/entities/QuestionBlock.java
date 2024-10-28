package entities;

import java.awt.Rectangle;

import graphics.GameGraphicElement;
import colliders.QuestionBlockCollider;
import entities.powerUp.GreenMushroom;
import entities.powerUp.PowerUp;
import entities.powerUp.Star;
import game.GraphicEngine;
import game.LevelReader;

public class QuestionBlock implements Entity {
    protected static String SPRITES_FOLDER = "questionBlock";
    protected QuestionBlockCollider collider;
    protected GameGraphicElement graphicElement;
    protected boolean depends;
    protected UpdatableEntity entity;

    public QuestionBlock(char s) {
        collider = new QuestionBlockCollider(this, new Rectangle(32, 32));
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);

        if (s == 's' || s == 'f') {
            depends = true;
        } else if (s == '1') {
            entity = new Coin();
        } else if (s == 'S') {
            entity = new Star();
        } else if (s == 'v') {
            entity = new GreenMushroom();
        }
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public QuestionBlockCollider getCollider() {
        return collider;
    }

    public void setDepends(boolean b) {
        depends = b;
    }

    public boolean getDepends() {
        return depends;
    }

    public void interaction() {
        releaseEntity(entity);
    }

    public void interaction(PowerUp p) {
        entity = p;
        releaseEntity(entity);
    }

    public void releaseEntity(UpdatableEntity e) {
    }

    public void releaseEntity(PowerUp p) {
        int powerUpGraphicX = (int) graphicElement.getPosition().getX();
        int powerUpGraphicY = (int) graphicElement.getPosition().getY();

        p.getGraphicElement().setPosition(powerUpGraphicX, powerUpGraphicY + LevelReader.CHUNK);

        int powerUpColliderX = (int) collider.getPosition().getX();
        int powerUpColliderY = (int) collider.getPosition().getY();

        p.getCollider().setPosition(powerUpColliderX, powerUpColliderY + LevelReader.CHUNK);
        p.getCollider().activate();

        GraphicEngine.instance().add(p.getGraphicElement());
        p.load();
    }

    public void releaseEntity(Coin c) {
    }
}
