package entities;

import java.awt.Rectangle;

import colliders.GoombaCollider;
import game.CollisionsEngine;       
import game.Game;
import game.GraphicEngine;
import graphics.GameGraphicElement;
import java.util.Timer;
import java.util.TimerTask;

public class Goomba extends BaseUpdatableEntity implements Enemy {
    protected static String SPRITES_FOLDER = "goomba";
    protected boolean movingRight = true;
    protected int speedX;

    public Goomba() {
        speedX = 2;
        collider = new GoombaCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER, Game.instance().getMode());
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
        graphicElement.translate(0, -9);
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run(){
                GraphicEngine.instance().removeGraphicElement(graphicElement);
            }
        };

        timer.schedule(task,1000);
    }

    @Override
    public int getPoints() {
        throw new UnsupportedOperationException("Unimplemented method 'getPoints'");
    }

    public void switchDirection() {
        movingRight  = !movingRight;
    }

    @Override
    public void update() {
        int moveX = movingRight ? speedX : -speedX;
        graphicElement.translate(moveX, 0);
        collider.translate(moveX, 0);

        graphicElement.translate(0, -3);
        collider.translate(0, -3);
    }
    
}
