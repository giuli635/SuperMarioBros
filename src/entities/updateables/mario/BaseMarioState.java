package entities.updateables.mario;

import java.util.Timer;
import java.util.TimerTask;

import colliders.updateables.mario.InvulnerableCollider;
import colliders.updateables.mario.MarioCollider;
import entities.updateables.mario.actions.DisappearSprite;
import graphics.GameGraphicElement;

public abstract class BaseMarioState implements MarioState {
    protected Mario mario;
    protected MarioCollider previousCollider;
    protected String newSpritesFolder;
    protected String previousSpritesFolder; 

    public BaseMarioState(Mario m) {
        mario = m;
    }

    protected void swapCollider(MarioCollider newCollider) {
        previousCollider = mario.getCollider();
        newCollider.copy(previousCollider);
        previousCollider.track(newCollider);
        mario.setUnderlyingCollider(newCollider);

        GameGraphicElement graphicElement = mario.getGraphicElement();
        previousSpritesFolder = graphicElement.getFolder();
        mario.setSpritesFolder(newSpritesFolder);
    }

    protected void revertState() {
        MarioCollider newCollider = new InvulnerableCollider(mario);
        newCollider.copy(mario.getCollider());
        mario.setCollider(newCollider);
        mario.setUnderlyingCollider(previousCollider);
        
        mario.setSpritesFolder(previousSpritesFolder);
        mario.addAction(new DisappearSprite());

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                MarioCollider collider = mario.getCollider().getBaseCollider();
                collider.copy(mario.getCollider());
                mario.setCollider(collider);
                mario.removeAction(new DisappearSprite());
            }
        };

        timer.schedule(task, 3000);
    }
}
