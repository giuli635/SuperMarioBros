package entities.updateables.enemies;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import colliders.updateables.enemies.PiranhaPlantCollider;
import game.GraphicEngine;
import graphics.GameGraphicElement;

public class PiranhaPlant extends BaseEnemy {
    protected static final float maxHeight = 1.5f;
    public final static int POINTS = 30;
    protected float speedY;
    
    protected static String SPRITES_FOLDER = "piranha";
    public final static String[] PIRANHA_EATING = {"piranhaOpen", "piranhaClosed"};
    protected int animationFrameCounter = 0;
    protected int framesPerSprite = 10;
    protected int changingSprite;
    
    protected boolean movingUp;
    

    protected PiranhaPlantCollider collider;
    protected GameGraphicElement graphicElement;

    public PiranhaPlant() {
        movingUp = true;
        collider = new PiranhaPlantCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite("piranhaOpen");
        speedX = 0;
        speedY = 0f;
    }

    @Override
    public void recieveDamage() {
        collider.deactivate();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run(){
                GraphicEngine.instance().remove(graphicElement);
                unload();
            }
        };

        timer.schedule(task,1000);
    }

    @Override
    public void update() {
        handleVerticalMovement();
        setChangeableSprites();
    }

    private void handleVerticalMovement() {
        if (movingUp) {
            speedY = speedY + 0.01f;
            if (speedY >= maxHeight) {
                switchDirection();
                speedY = 0;
            }
        } else {
            speedY = speedY - 0.01f;
            if (speedY <= -maxHeight) {
                switchDirection();
                speedY = 0;
            }
        }
        translate(speedX, (int) speedY);
        setChangeableSprites();
        
    }

    public void switchDirection() {
        movingUp = !movingUp;
    }

    private void setChangeableSprites() {
        changingSprite += (animationFrameCounter %= framesPerSprite) == 0 ? 1 : 0;
        changingSprite %= 2;
        animationFrameCounter++;
        setSprite(PIRANHA_EATING[changingSprite]);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public PiranhaPlantCollider getCollider() {
        return collider;
    }

    @Override
    public int pointsToAdd() {
        return POINTS;
    }

    @Override
    public int pointsToSubtract() {
        return -(POINTS);
    }
}
