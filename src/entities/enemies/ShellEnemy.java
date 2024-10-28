package entities.enemies;

public interface ShellEnemy extends Enemy{
    public void setShell(boolean s);
    public boolean getShell();
    public void setSpeedX(int s);
    public int getSpeedX();
}
