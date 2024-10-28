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
    protected boolean active;
    protected UpdatableEntity entity;

    public QuestionBlock(char s) {
        collider = new QuestionBlockCollider(this, new Rectangle(32, 32));
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        active = true;

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
        entity = null;
        active = false;
        graphicElement.setSprite(SPRITES_FOLDER);
    }

    public void interaction(PowerUp p) {
        entity = p;
        releaseEntity(entity);
        entity = null;
        active = false;
        graphicElement.setSprite(SPRITES_FOLDER);
    }

    public void releaseEntity(UpdatableEntity e) {
        int entityGraphicX = (int) graphicElement.getPosition().getX();
        int entityGraphicY = (int) graphicElement.getPosition().getY();

        e.getGraphicElement().setPosition(entityGraphicX, entityGraphicY + LevelReader.CHUNK);

        int entityColliderX = (int) collider.getPosition().getX();
        int entityColliderY = (int) collider.getPosition().getY();

        e.getCollider().setPosition(entityColliderX, entityColliderY + LevelReader.CHUNK);
        e.getCollider().activate();

        GraphicEngine.instance().add(e.getGraphicElement());
        e.load();
    }

    public void releaseEntity(Coin c) {
    }

    public boolean getActive() {
        return active;
    }
}
