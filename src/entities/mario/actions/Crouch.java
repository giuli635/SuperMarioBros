package entities.mario.actions;

import java.awt.event.KeyEvent;

import entities.mario.Mario;
import game.Game;
import game.KeyStatus;
import graphics.GameGraphicElement;

public class Crouch extends BaseMarioAction {
    public static final int DEFAULT_CROUCH_PRIORITY = 100;
    protected int priority;

    public Crouch() {
        priority = DEFAULT_CROUCH_PRIORITY;
    }

    @Override
    public void execute(Mario mario) {
        if (Game.instance().getKeyStatus(KeyEvent.VK_S) == KeyStatus.PRESSED) {
            GameGraphicElement graphicElement = mario.getGraphicElement();
            graphicElement.setSprite(Mario.MARIO_CROUCHING);
        }
    }
}
