package colliders.updateables.powerups;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.updateables.mario.FireMarioCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.StarMarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import collisions.updateables.powerups.GreenMushroomCollision;
import entities.updateables.mario.Mario;
import entities.updateables.mario.states.StarMario;
import entities.updateables.powerups.GreenMushroom;
import entities.updateables.powerups.Star;
import utils.Axis;

public class GreenMushroomCollider extends PowerUpCollider {
    protected GreenMushroom m;

    public GreenMushroomCollider(GreenMushroom mushroom, Rectangle b) {
        super(b);
        m = mushroom;
    }
    
    @Override
    public GreenMushroom getEntity() {
        return m;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public GreenMushroomCollision getCollision() {
        return new GreenMushroomCollision(this);
    }

    public void handleCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        super.handleCollision(m);
        Collision(mario);
    }
    
    public void handleCollision(SuperMarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        super.handleCollision(m);
        Collision(mario);
    }

    public void handleCollision(FireMarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        super.handleCollision(m);
        Collision(mario);
    }

    public void handleCollision(StarMarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        super.handleCollision(m);
        Collision(mario);
    }

    private void Collision(Mario m){
        m.modifyPoints(GreenMushroom.POINTS);
        m.addLives();
    }
}
