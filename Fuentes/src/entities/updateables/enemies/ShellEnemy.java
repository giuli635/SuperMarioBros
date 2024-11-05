package entities.updateables.enemies;

public interface ShellEnemy extends Enemy {
    public static final int SHELL_SPEED = 6;
    public void setShell(boolean s);
    public boolean getShell();
}
