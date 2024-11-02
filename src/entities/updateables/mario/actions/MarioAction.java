package entities.updateables.mario.actions;

import entities.updateables.mario.Mario;
import utils.Prioritizable;

public interface MarioAction extends Prioritizable {
    public void execute(Mario m);
}

