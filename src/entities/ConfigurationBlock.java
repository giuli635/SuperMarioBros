package entities;

import java.awt.Rectangle;

import colliders.LanguageSwitcherCollider;
import colliders.ModeSwitcherCollider;
import colliders.solids.SolidCollider;
import entities.updateables.Animator;
import entities.updateables.UpdateableBody;
import game.LevelReader;
import graphics.GameGraphicElement;
import graphics.TextLabel;

public class ConfigurationBlock extends UpdateableBody {
    protected static String SPRITES_FOLDER = "configurationBlock";
    public final static String[] ANIMATED_SPRITES = {"configurationBlock", "configurationBlock2", "configurationBlock3"};
    public static final int FRAMES_PER_SPRITE = 10;

    protected SolidCollider collider;
    protected GameGraphicElement graphicElement;
    protected TextLabel text;
    protected Animator animator;

    public ConfigurationBlock(char s) {
        animator = new Animator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);

        if (s == 'm') {
            collider = new ModeSwitcherCollider(this, new Rectangle());
            text = new TextLabel("modeChange");
        } else if (s == 'l') {
            collider = new LanguageSwitcherCollider(this, new Rectangle());
            text = new TextLabel("languageChange");
        }

        setSprite(SPRITES_FOLDER);
    }

    @Override
    public void load() {
        super.load();
        if (text != null) {
            text.add();
            text.setPosition((int) graphicElement.getPosition().getX() - text.getComponent().getWidth() / 2 + LevelReader.CHUNK, (int) graphicElement.getPosition().getY() + 50);
        }
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;

    }

    @Override
    public SolidCollider getCollider() {
        return collider;
    }

    @Override
    public void update() {
        animator.animate();
    }
    
}
