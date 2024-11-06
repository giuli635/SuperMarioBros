package entities.updateables.mario.states;

import utils.Prioritizable;

public interface CommandMarioStatus extends Prioritizable {
    public void setStatus();
    public void removeStatus();
}
