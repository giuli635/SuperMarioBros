package colliders.updateables.enemies;

import java.awt.Rectangle;

import collisions.VisitorCollision;
import collisions.updateables.enemies.PiranhaPlantCollision;
import collisions.updateables.mario.InvulnerableCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import entities.updateables.enemies.PiranhaPlant;
import entities.updateables.mario.Mario;
import entities.updateables.mario.states.Invulnerable;
import utils.Axis;

public class PiranhaPlantCollider extends EnemyCollider {
    protected PiranhaPlant piranha;

    public PiranhaPlantCollider(PiranhaPlant p, Rectangle b) {
        super(b);
        piranha = p;
    }

    @Override
    public void recieveCollision(VisitorCollision c, Axis a) {
       c.collide(this, a);
    }

    @Override
    public PiranhaPlantCollision getCollision() {
        return new PiranhaPlantCollision(this);
    }

    @Override
    public PiranhaPlant getEntity() {
        return piranha;
    }

    @Override
    public void handleVerticalCollision(InvulnerableCollision m) {
    }

    @Override
    public void handleVerticalCollision(MarioCollision m) {
        kill(m.getCollider().getEntity());
    }

    public void handleVerticalCollision(SuperMarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        mario.removeState(m.getCollider().getAssociatedState());
        mario.setState(new Invulnerable(mario));
    }
}
