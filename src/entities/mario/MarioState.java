package entities.mario;

public interface MarioState {
    public void setCollisions(Mario m);
    public void setFunctionality(Mario m);
    public void removeState(Mario m);
}
