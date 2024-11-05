package entities.updateables.mario.actions;

import utils.Prioritizable;

public interface MarioAction extends Prioritizable {
    public void execute();
}

