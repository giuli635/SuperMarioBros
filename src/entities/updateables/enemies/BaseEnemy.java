package entities.updateables.enemies;

import java.util.Timer;
import java.util.TimerTask;

import entities.updateables.BaseMovableEntity;
import game.GraphicEngine;
import graphics.GameGraphicElement;

public abstract class BaseEnemy extends BaseMovableEntity implements Enemy {
    public static final float DEFAULT_SPEEDX = -2f;
    public static final float DEFAULT_SPEEDY = 0f;
    public static final String STOMP_SOUND = "stomp.wav";
    public static final String DIE_FIRE_SOUND = "kick.wav";

    public BaseEnemy() {
        falling = false;
        speedX = DEFAULT_SPEEDX;
        speedY = DEFAULT_SPEEDY;
    }

    public void die(String sprite) {
        getCollider().deactivate();
        unload();

        GameGraphicElement graphicElement = getGraphicElement();
        setSprite(sprite);
        GraphicEngine.instance().moveToBack(graphicElement);
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run(){
                GraphicEngine.instance().remove(graphicElement);
            }
        };

        timer.schedule(task, 1000);
    }
}
