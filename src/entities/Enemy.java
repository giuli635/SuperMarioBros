package entities;

public abstract class Enemy extends GameEntity{
    abstract public void getDamage();
    abstract public int getPoints();
    abstract public Entity clone();
    abstract public void update();
}
