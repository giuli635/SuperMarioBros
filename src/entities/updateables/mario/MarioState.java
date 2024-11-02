package entities.updateables.mario;

import utils.Prioritizable;

public interface MarioState extends Prioritizable {
    public void setState();
    public void removeState();
}
