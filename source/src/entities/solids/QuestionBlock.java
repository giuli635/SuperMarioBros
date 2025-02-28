package entities.solids;

import java.awt.Rectangle;
import java.util.List;

import colliders.solids.QuestionBlockCollider;
import entities.updateables.MovementAnimator;
import entities.updateables.Coin;
import entities.updateables.ObserverUpdateableEntity;
import entities.updateables.UpdateableBody;
import entities.updateables.powerups.GreenMushroom;
import entities.updateables.powerups.PowerUp;
import entities.updateables.powerups.Star;
import graphics.GameGraphicElement;
import game.LevelReader;
import game.SingletonSoundManager;

public class QuestionBlock extends UpdateableBody {
    protected static String SPRITES_FOLDER = "questionBlock";
    public static final List<String> ANIMATED_SPRITES = List.of(
        "questionBlock", "questionBlock2"
    );
    public static final int FRAMES_PER_SPRITE = 10;

    protected QuestionBlockCollider collider;
    protected GameGraphicElement graphicElement;
    protected boolean depends;
    protected boolean active;
    protected ObserverUpdateableEntity entity;
    protected MovementAnimator animator;

    public QuestionBlock(char s) {
        animator = new MovementAnimator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
        collider = new QuestionBlockCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
        active = true;

        if (s == 's' || s == 'f') {
            depends = true;
        } else if (s == '$') {
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
        spawnEntity(entity, 0, LevelReader.CHUNK);
        entity = null;
        active = false;
        setSprite("questionBlockHit");
        SingletonSoundManager.instance().playSound("powerupAppears.wav");
    }

    public boolean getActive() {
        return active;
    }

    @Override
    public void update() {
        if (active) {
            animator.animate();
        }
    }
}
