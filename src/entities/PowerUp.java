package entities;

import javax.swing.ImageIcon;

import colliders.PowerUpCollider;

public abstract class PowerUp extends GameEntity{
    public abstract void apply(Mario m);
    public abstract void move();
}
