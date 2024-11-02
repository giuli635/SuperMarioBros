package entities.updateables;

import entities.Body;
import game.Game;

public class Animator {
    protected String[] animatedSprites;
    protected Body entity;
    
    protected int framesPerSprite;
    protected int currentSprite;

    public int getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(int currentSprite) {
        this.currentSprite = currentSprite;
    }

    public int getFramesPerSprite() {
        return framesPerSprite;
    }

    public void setFramesPerSprite(int framesPerSprite) {
        this.framesPerSprite = framesPerSprite;
    }

    public Animator(String[] sprites, int framesPerSprite, Body e) {
        this.framesPerSprite = framesPerSprite;
        animatedSprites = sprites;
        entity = e;
    }
    
    public void animate() {
        boolean flipped = entity.getGraphicElement().isFlipped();
        manageChangeableSprites();
        if (flipped) {
            entity.getGraphicElement().flipSprite();
        }
    }

    protected void manageChangeableSprites() {
        currentSprite += (Game.instance().getFrames() % framesPerSprite) == 0 ? 1 : 0;
        currentSprite %= animatedSprites.length;
        entity.setSprite(animatedSprites[currentSprite]);
    }
}
