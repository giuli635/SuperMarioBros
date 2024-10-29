package entities.updateables.mario.actions;

import entities.updateables.mario.Mario;

public interface MarioAction {
    public int getPriority();
    public void setPriority(int p);
    public void execute(Mario m);
}

