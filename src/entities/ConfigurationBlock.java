package entities;

import java.awt.Rectangle;

import colliders.LanguageSwitcherCollider;
import colliders.ModeSwitcherCollider;
import colliders.solids.SolidCollider;
import entities.updateables.BaseAnimatedEntity;
import game.LevelReader;
import graphics.GameGraphicElement;
import graphics.TextLabel;

public class ConfigurationBlock extends BaseAnimatedEntity{
    protected static String SPRITES_FOLDER = "configurationBlock";
    public final static String[] ANIMATED_SPRITES = {"configurationBlock", "configurationBlock2", "configurationBlock3"};
    protected SolidCollider collider;
    protected GameGraphicElement graphicElement;
    protected TextLabel text;

    public ConfigurationBlock(char s) {
        animatedSprites = ANIMATED_SPRITES;
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
    
}
