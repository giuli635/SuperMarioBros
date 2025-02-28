
package entities.updateables.mario.states;

import java.util.Timer;
import java.util.TimerTask;

import colliders.updateables.mario.InvulnerableCollider;
import colliders.updateables.mario.MarioCollider;
import entities.updateables.mario.Mario;
import entities.updateables.mario.actions.DisappearSprite;

public class Invulnerable extends BaseMarioStatus {
    protected static final int PRIORITY = 1;
    protected MarioCollider invulnerableCollider;
    protected Timer timer;
    protected DisappearSprite disappearSprite;

    public Invulnerable(Mario m) {
        super(m);
        priority = PRIORITY;
        invulnerableCollider = null;
    }

    @Override
    public void setStatus() {
        invulnerableCollider = new InvulnerableCollider(mario);

        mario.setCollider(invulnerableCollider);

        disappearSprite = new DisappearSprite(mario);
        mario.addAction(disappearSprite);

        Invulnerable thisState = this;
        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
               mario.removeState(thisState);
            }
        };

        timer.schedule(task, 3000);
    }

    @Override
    public void removeStatus() {
        timer.cancel();
        timer.purge();
        mario.removeCollider(invulnerableCollider);
        mario.removeAction(disappearSprite);
    }
}
