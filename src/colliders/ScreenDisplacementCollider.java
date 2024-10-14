package colliders;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.MarioCollision;
import collisions.ScreenDisplacementCollision;
import entities.Entity;

public class ScreenDisplacementCollider extends BaseCollider {
    public ScreenDisplacementCollider(Rectangle b) {
        super(b);
    }

    public Entity getEntity() {
        return null;
    }

    @Override
    public Collision getCollision() {
        return new ScreenDisplacementCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Direction d) {
        c.collide(this, d);
    }

    public void handleCollision(MarioCollision m, Direction d) {
        translate((int) m.getCollider().getVelocity().getXComponent(), 0);
    }

    // public void scrollScreen() {  
    //     int screenX = Screen.instance().getX();
    //     Dimension screenSize = GraphicEngine.instance().getPanelSize();
    //     int screenWidth = screenSize.width;
    //     int screenRight = screenWidth + screenX;
    //     int levelEnd = CollisionsEngine.instance().pixelChunkCount();
    //     if(screenX > 0 && screenRight != levelEnd) {
    //         CollisionsEngine collisionsEngine = CollisionsEngine.instance();
    //     }
    // }
}
