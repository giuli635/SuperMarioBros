package utils;

public abstract class BasePrioritizable implements Prioritizable {
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
