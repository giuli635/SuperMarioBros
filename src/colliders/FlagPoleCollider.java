package colliders;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.FlagPoleCollision;
import collisions.updateables.mario.MarioCollision;
import entities.Entity;
import entities.FlagPole;
import game.Game;
import utils.Axis;

public class FlagPoleCollider extends BaseCollider{
    protected FlagPole flagPole;

    public FlagPoleCollider(FlagPole e, Rectangle b) {
        super(b);
        flagPole = e;
    }

    @Override
    public Entity getEntity() {
        return flagPole;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public Collision getCollision() {
        return new FlagPoleCollision(this);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        Game.instance().advanceLevel();
    }
}
