package entities.updateables.mario.states;

import java.util.Timer;
import java.util.TimerTask;

import colliders.updateables.mario.MarioCollider;
import colliders.updateables.mario.StarMarioCollider;
import entities.updateables.mario.Mario;

public class StarMario extends BaseMarioState {
    protected static final int PRIORITY = 2;
    protected MarioCollider starCollider;

    public StarMario(Mario m) {
        super(m);
        priority = PRIORITY;
        starCollider = null;
    }

    @Override
    public void setState() {
        starCollider = new StarMarioCollider(mario);

        mario.setCollider(starCollider);

        StarMario thisState = this;
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
        mario.removeCollider(starCollider);
    }
}
