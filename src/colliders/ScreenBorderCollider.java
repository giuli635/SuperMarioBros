package colliders;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.ScreenBorderCollision;
import entities.Entity;

public class ScreenBorderCollider extends BaseCollider {
    public ScreenBorderCollider(Rectangle b) {
        super(b);
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public void sendCollision(Collision c, Direction d) {
        c.collide(this, d);
    }

    @Override
    public Collision getCollision() {
        return new ScreenBorderCollision(this);
    }

    public void handleCollision(MarioCollider m, Direction d) {
        //Por qué no funcionaaaaaaaaaaaaaaaa!!!!?
        int velocity = (int) m.getVelocity().getXComponent();
        m.getEntity().getGraphicElement().translate(-velocity, 0);
        m.translate(-velocity, 0);
        System.out.println("Llega la colisión");
    }
    
}
