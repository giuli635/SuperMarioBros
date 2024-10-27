package entities.enemies;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import colliders.enemies.SpinyCollider;
import entities.BaseUpdatableEntity;
import entities.Entity;
import game.GraphicEngine;
import graphics.GameGraphicElement;

public class Spiny extends BaseUpdatableEntity implements Enemy {
    protected static String SPRITES_FOLDER = "spiny";
    protected int speedX;

    protected SpinyCollider collider;
    protected GameGraphicElement graphicElement;

    public Spiny() {
        super();
        speedX = -2;
        collider = new SpinyCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public void recieveDamage() {
        collider.deactivate();

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
        return 60;
    }
    
    public int subtractPoints(){
        return 30;
    }

    @Override
    public Entity clone() {
       return new Spiny();
    }

    public void switchDirection() {
        speedX = -speedX;
        graphicElement.flipSprite();
    }

    @Override
    public void update() {
        graphicElement.translate(speedX, 0);
        collider.translate(speedX, 0);
        graphicElement.translate(0, -3);
        collider.translate(0, -3);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public SpinyCollider getCollider() {
        return collider;
    }
}
