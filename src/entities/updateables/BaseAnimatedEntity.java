package entities.updateables;

public abstract class BaseAnimatedEntity extends UpdateableBody {
    public String[] animatedSprites;

    protected int animationFrameCounter = 0;
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

    private void manageChangeableSprites() {
        changingSprite += (animationFrameCounter %= framesPerSprite) == 0 ? 1 : 0;
        changingSprite %= 2;
        animationFrameCounter++;
        getGraphicElement().setSprite(animatedSprites[changingSprite]);
    }


}
