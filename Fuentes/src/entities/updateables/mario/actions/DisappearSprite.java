package entities.updateables.mario.actions;

import entities.updateables.mario.Mario;
import game.Game;
import utils.BasePrioritizable;

public class DisappearSprite extends BasePrioritizable implements MarioAction {
    public static final int DEFAULT_PRIORITY = -100;
    protected static final int FRAMES_DISAPPEARING = 8;
    protected int disappear;
    protected Mario mario;

    public DisappearSprite(Mario m) {
        priority = DEFAULT_PRIORITY;
        disappear = 0;
        mario = m;
    }

    @Override
    public void execute() {
        disappear += (Game.instance().getFrames() % FRAMES_DISAPPEARING == 0) ? 1 : 0;
        if ((disappear %= 2) == 0) {
            mario.getGraphicElement().setSprite(null);
        }
    }
}
