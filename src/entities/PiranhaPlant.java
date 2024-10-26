package entities;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import colliders.PiranhaPlantCollider;
import game.CollisionsEngine;
import game.Game;
import game.GraphicEngine;
import graphics.GameGraphicElement;

public class PiranhaPlant extends BaseUpdatableEntity implements Enemy{
    protected float speedY = 0f;
    protected static final float maxHight = 1.5f;
    protected static String SPRITES_FOLDER = "piranha";
    protected boolean movingUp = false;
    protected boolean adjusted = false;

    public PiranhaPlant() {
        collider = new PiranhaPlantCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
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
       Game.instance().unregisterToUpdate(this);
        CollisionsEngine.instance().remove(collider);
        //graphicElement.setSprite(SPRITES_FOLDER + "Death");
        graphicElement.translate(0, -9);
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run(){
                GraphicEngine.instance().remove(graphicElement);
            }
        };

        timer.schedule(task,1000);
    }

    @Override
    public int getPoints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPoints'");
    }

    @Override
    public void update() {
        handleVerticalMovement();
        adjustPiranha();
    }

    private void handleVerticalMovement() {
        if (movingUp) {
            graphicElement.translate(0, (int) speedY);
            collider.translate(0, (int) speedY);
            speedY = speedY + 0.01f;
            if (speedY >= maxHight) {
                switchDirection();
                speedY = 0;
            }
        } else {
            graphicElement.translate(0, (int) speedY);
            collider.translate(0, (int) speedY);
            speedY = speedY - 0.01f;
            if (speedY <= -maxHight) {
                switchDirection();
                speedY = 0;
            }
        }
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
}
