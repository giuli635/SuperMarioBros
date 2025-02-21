package entities.updateables.mario.states;

import colliders.updateables.mario.MarioCollider;
import entities.updateables.mario.Mario;
import graphics.GameGraphicElement;
import utils.BasePrioritizable;

public abstract class BaseMarioStatus extends BasePrioritizable implements CommandMarioStatus {
    protected Mario mario;
    protected MarioCollider previousCollider;
    protected String newSpritesFolder;
    protected String previousSpritesFolder; 

    public BaseMarioStatus(Mario m) {
        mario = m;
    }

    protected void swapSprites() {
        GameGraphicElement graphicElement = mario.getGraphicElement();
        previousSpritesFolder = graphicElement.getFolder();
        mario.setSpritesFolder(newSpritesFolder);
    }

    protected void revertSprites() {
        mario.setSprite(Mario.MARIO_STILL);
        mario.setSpritesFolder(previousSpritesFolder);
    }
}
