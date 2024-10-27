package entities.enemies;

import java.awt.Rectangle;

import colliders.enemies.GoombaCollider;
import entities.BaseUpdatableEntity;
import entities.Entity;
import game.CollisionsEngine;       
import game.Game;
import game.GraphicEngine;
import graphics.GameGraphicElement;
import java.util.Timer;
import java.util.TimerTask;

public class Goomba extends BaseUpdatableEntity implements Enemy {
    protected static String SPRITES_FOLDER = "goomba";
    protected int speedX;

    protected GoombaCollider collider;
    protected GameGraphicElement graphicElement;

    public Goomba() {
        speedX = -2;
        collider = new GoombaCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public Entity clone() {
        return new Goomba();
    }

    @Override
    public void recieveDamage() {
        Game.instance().unregisterToUpdate(this);
        CollisionsEngine.instance().remove(collider);
        graphicElement.setSprite(SPRITES_FOLDER + "Death");
        
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
        throw new UnsupportedOperationException("Unimplemented method 'getPoints'");
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
    public GoombaCollider getCollider() {
        return collider;
    }
    
}
