package entities.enemies;

import java.awt.Rectangle;

import entities.BaseUpdatableEntity;
import entities.Entity;
import game.CollisionsEngine;       
import game.Game;
import game.GraphicEngine;
import graphics.GameGraphicElement;
import java.util.Timer;
import java.util.TimerTask;

import colliders.enemies.BuzzyBeetleCollider;

public class BuzzyBeetle extends BaseUpdatableEntity implements Enemy {
    protected static String SPRITES_FOLDER = "buzzyBeetle";
    protected int speedX;

    protected BuzzyBeetleCollider collider;
    protected GameGraphicElement graphicElement;

    public BuzzyBeetle() {
        speedX = -2;
        collider = new BuzzyBeetleCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public Entity clone() {
        return new BuzzyBeetle();
    }

    @Override
    public void recieveDamage() {
        Game.instance().unregisterToUpdate(this);
        CollisionsEngine.instance().remove(collider);
        graphicElement.setSprite(SPRITES_FOLDER + "Shell");
        
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
        return 30;
    }
    
    public int subtractPoints(){
        return 15;
    }

    public void switchDirection() {
        speedX = -speedX;
        graphicElement.flipSprite();
    }

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
    public BuzzyBeetleCollider getCollider() {
        return collider;
    }
}
