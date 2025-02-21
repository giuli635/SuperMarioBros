package entities.updateables.mario.actions;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import entities.updateables.ColorAnimator;
import entities.updateables.mario.Mario;
import utils.BasePrioritizable;

public class ChangeMarioColors extends BasePrioritizable implements StrategyMarioAction {
    public static final int DEFAULT_PRIORITY = -50;
    protected static final int FRAMES_PER_COLORSCHEME = 5;
    protected ColorAnimator animator;

    public ChangeMarioColors(List<Map<Color, Color>> colorsMapping, Mario m) {
        priority = DEFAULT_PRIORITY;
        animator = new ColorAnimator(colorsMapping, FRAMES_PER_COLORSCHEME, m);
    }

    @Override
    public void execute() {
        animator.animate();
    }
}
