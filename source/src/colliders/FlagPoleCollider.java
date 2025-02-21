package colliders;

import java.awt.Rectangle;

import collisions.VisitorCollision;
import collisions.FlagPoleCollision;
import collisions.updateables.mario.MarioCollision;
import entities.Entity;
import entities.FlagPole;
import game.SingletonGame;
import game.SingletonSoundManager;
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
    public void recieveCollision(VisitorCollision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public VisitorCollision getCollision() {
        return new FlagPoleCollision(this);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        SingletonGame.instance().advanceLevel();
        SingletonSoundManager.instance().playSound("flagpole.wav");
    }
}
