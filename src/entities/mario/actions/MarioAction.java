package entities.mario.actions;

import entities.mario.Mario;

public interface MarioAction {
    public int getPriority();
    public void setPriority(int p);
    public void execute(Mario m);
}

