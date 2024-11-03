package entities.updateables.mario.states;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import colliders.updateables.mario.MarioCollider;
import colliders.updateables.mario.StarMarioCollider;
import entities.updateables.mario.Mario;
import entities.updateables.mario.actions.ChangeMarioColors;

public class StarMario extends BaseMarioState {
    protected static final int PRIORITY = 2;
    protected static final List<Map<Color, Color>> STAR_MARIO_COLORS = initStarColors();
    protected MarioCollider starCollider;
    protected ChangeMarioColors colorChanger;

    protected static List<Map<Color, Color>> initStarColors() {
        Map<Color, Color> secondPallete = Map.of(
            new Color(12, 147, 0, 255), new Color(247, 216, 165, 255)
        );

        Map<Color, Color> thirdPallete = Map.of(
            new Color(234, 158, 34, 255), new Color(153, 78, 0, 255),
            new Color(247, 216, 165, 255), new Color(0, 0, 0, 255),
            new Color(255, 254, 255, 255), new Color(254, 204, 197, 255)
        );

        return List.of(secondPallete, thirdPallete);
    }

    public StarMario(Mario m) {
        super(m);
        priority = PRIORITY;
        starCollider = null;
    }

    @Override
    public void setState() {
        starCollider = new StarMarioCollider(mario, this);

        mario.setCollider(starCollider);

        List<Map<Color, Color>> colorsMapping = new ArrayList<>(STAR_MARIO_COLORS);
        colorsMapping.addFirst(mario.getInitialColorStarMario());
        colorChanger = new ChangeMarioColors(colorsMapping, mario);
        mario.addAction(colorChanger);

        // StarMario thisState = this;
        // Timer timer = new Timer();
        // TimerTask task = new TimerTask() {
        //     public void run() {
        //         mario.removeState(thisState);
        //     }
        // };
        //
        // timer.schedule(task, 10000);
    }

    @Override
    public void removeState() {
        mario.removeCollider(starCollider);
        mario.removeAction(colorChanger);
    }
}
