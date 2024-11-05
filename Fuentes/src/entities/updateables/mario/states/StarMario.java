package entities.updateables.mario.states;

import java.util.Timer;
import java.util.TimerTask;

import colliders.updateables.mario.MarioCollider;
import colliders.updateables.mario.StarMarioCollider;
import entities.updateables.mario.Mario;
import entities.updateables.mario.actions.ChangeMarioColors;

public class StarMario extends BaseMarioState {
    protected static final int PRIORITY = 2;
    protected MarioCollider starCollider;
    protected ChangeMarioColors colorChanger;
    protected Timer timer;

    public StarMario(Mario m) {
        super(m);
        priority = PRIORITY;
        starCollider = null;
    }

    @Override
    public void setState() {
        starCollider = new StarMarioCollider(mario, this);

        mario.setCollider(starCollider);

        colorChanger = new ChangeMarioColors(mario.getColorStarMarioColors(), mario);
        mario.addAction(colorChanger);

        StarMario thisState = this;
        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                mario.removeState(thisState);
            }
        };

        timer.schedule(task, 10000);
    }

    @Override
    public void removeState() {
        timer.cancel();
        timer.purge();
        mario.removeCollider(starCollider);
        mario.removeAction(colorChanger);
        mario.getGraphicElement().removeColorRemap();
    }
}
