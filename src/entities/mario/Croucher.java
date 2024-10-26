package entities.mario;

import java.awt.event.KeyEvent;

import colliders.Collider;
import entities.BaseUpdatableEntity;
import game.Game;
import game.KeyStatus;
import graphics.GameGraphicElement;

public class Croucher extends BaseUpdatableEntity {
    protected static final String MARIO_CROUCHING = "marioCrouching";
    protected Mario mario;

    public Croucher(Mario m) {
        mario = m;
    }

    @Override
    public void update() {
        if (Game.instance().getKeyStatus(KeyEvent.VK_S) == KeyStatus.PRESSED) {
            mario.listenToKeys(false);
            GameGraphicElement graphicElement = mario.getGraphicElement();
            graphicElement.setSprite(MARIO_CROUCHING);
        } else {
            mario.listenToKeys(true);
        }
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return null;
    }

    @Override
    public Collider getCollider() {
        return null;
    }
}

