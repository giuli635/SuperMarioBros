package entities.mario.actions;

public abstract class BaseMarioAction implements MarioAction {
    protected int priority;

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int p) {
        priority = p;
    }
}
