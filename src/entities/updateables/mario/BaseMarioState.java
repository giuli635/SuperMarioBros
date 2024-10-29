package entities.updateables.mario;

public abstract class BaseMarioState implements MarioState {
    protected Mario mario;

    public BaseMarioState(Mario m) {
        mario = m;
    }
}
