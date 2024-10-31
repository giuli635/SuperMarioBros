package entities.solids;

import java.awt.Rectangle;

import colliders.solids.QuestionBlockCollider;
import entities.updateables.BaseAnimatedEntity;
import entities.updateables.Coin;
import entities.updateables.UpdatableEntity;
import entities.updateables.powerups.GreenMushroom;
import entities.updateables.powerups.PowerUp;
import entities.updateables.powerups.Star;
import graphics.GameGraphicElement;
import game.GraphicEngine;
import game.LevelReader;

public class QuestionBlock extends BaseAnimatedEntity {
    protected static String SPRITES_FOLDER = "questionBlock";
    public final static String[] ANIMATED_SPRITES = {"questionBlock", "questionBlock2"};
    protected QuestionBlockCollider collider;
    protected GameGraphicElement graphicElement;
    protected boolean depends;
    protected boolean active;
    protected UpdatableEntity entity;

    public QuestionBlock(char s) {
        animatedSprites = ANIMATED_SPRITES;
        collider = new QuestionBlockCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
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

    public void interaction(PowerUp p) {
        if (depends) {
            entity = p;
        }
        releaseEntity(entity);
        entity = null;
        active = false;
        animatedSprites = null;
        setSprite("questionBlockHit");
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
