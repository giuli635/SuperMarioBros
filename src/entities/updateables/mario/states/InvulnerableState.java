
package entities.updateables.mario.states;

import java.util.Timer;
import java.util.TimerTask;

import colliders.updateables.mario.InvulnerableCollider;
import colliders.updateables.mario.MarioCollider;
import entities.updateables.mario.Mario;
import entities.updateables.mario.actions.DisappearSprite;
import game.Game;

public class InvulnerableState extends BaseMarioState {
    protected static final int PRIORITY = 1;
    protected MarioCollider invulnerableCollider;

    public InvulnerableState(Mario m) {
        super(m);
        priority = PRIORITY;
        invulnerableCollider = null;
    }

    @Override
    public void setState() {
        invulnerableCollider = new InvulnerableCollider(mario);

        Game.instance().setDebugging(true);
        mario.setCollider(invulnerableCollider);

        mario.addAction(new DisappearSprite());

        InvulnerableState thisState = this;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                mario.removeState(thisState);
            }
        };

        timer.schedule(task, 3000);
    }

    @Override
    public void removeState() {
        mario.removeCollider(invulnerableCollider);
        mario.removeAction(new DisappearSprite());
    }
}
