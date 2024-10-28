package entities.enemies;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import colliders.enemies.PiranhaPlantCollider;
import entities.BaseUpdatableEntity;
import entities.Entity;
import game.GraphicEngine;
import graphics.GameGraphicElement;

public class PiranhaPlant extends BaseUpdatableEntity implements Enemy{
    protected static final float maxHeight = 1.5f;
    protected float speedY = 0f;
    
    protected static String SPRITES_FOLDER = "piranha";
    public final static String[] PIRANHA_EATING = {"piranhaOpen", "piranhaClosed"};
    protected int animationFrameCounter = 0;
    protected int framesPerSprite = 10;
    protected int changingSprite;
    
    protected boolean movingUp = false;
    protected boolean adjusted = false;
    

    protected PiranhaPlantCollider collider;
    protected GameGraphicElement graphicElement;

    public PiranhaPlant() {
        collider = new PiranhaPlantCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite("piranhaOpen");
        GraphicEngine.instance().moveToBack(graphicElement);
        
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    public Entity clone() {
        return new PiranhaPlant();
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
    public int getPoints() {
        return 30;
    }
    
    public int subtractPoints(){
        return 30;
    }

    @Override
    public void update() {
        handleVerticalMovement();
        adjustPiranha();
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
        graphicElement.translate(0, (int) speedY);
        collider.translate(0, (int) speedY);
        setChangeableSprites();
    }

    public void switchDirection() {
        movingUp = !movingUp;
    }

    private void adjustPiranha() {
        if (!adjusted) {
            graphicElement.translate(22, 0);
            collider.translate(22, 0);
            adjusted = true;
        }
    }

    private void setChangeableSprites() {
        changingSprite += (animationFrameCounter %= framesPerSprite) == 0 ? 1 : 0;
        changingSprite %= 2;
        animationFrameCounter++;
        graphicElement.setSprite(PIRANHA_EATING[changingSprite]);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public PiranhaPlantCollider getCollider() {
        return collider;
    }
}
