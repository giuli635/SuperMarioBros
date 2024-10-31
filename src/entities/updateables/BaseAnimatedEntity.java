package entities.updateables;

import game.Game;

public abstract class BaseAnimatedEntity extends UpdateableBody {
    public String[] animatedSprites;

    protected int framesPerSprite = 10;
    protected int changingSprite;
    
    public void update() {
        if (animatedSprites != null) {
            boolean flipped = getGraphicElement().isFlipped();
            manageChangeableSprites();
            if (flipped) {
                getGraphicElement().flipSprite();
            }
        }
    }

    protected void manageChangeableSprites() {
        changingSprite += (Game.instance().getFrames() % framesPerSprite) == 0 ? 1 : 0;
        changingSprite %= animatedSprites.length;
        setSprite(animatedSprites[changingSprite]);
    }
}
