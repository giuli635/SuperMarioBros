package entities.updateables.enemies;

import java.util.Timer;
import java.util.TimerTask;

import entities.updateables.BaseMovableEntity;
import game.GraphicEngine;
import graphics.GameGraphicElement;

public abstract class BaseEnemy extends BaseMovableEntity implements Enemy {
    public static final int DEFAULT_SPEEDX = -2;

    public BaseEnemy() {
        speedX = DEFAULT_SPEEDX;
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
