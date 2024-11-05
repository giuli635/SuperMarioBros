package colliders.invisibles;

import java.awt.Point;
import java.awt.Rectangle;

import colliders.BaseCollider;
import collisions.Collision;
import collisions.invisibles.LevelEndCollision;
import collisions.invisibles.ScreenBorderCollision;
import collisions.updateables.enemies.PiranhaPlantCollision;
import collisions.updateables.mario.MarioCollision;
import entities.Entity;
import game.GraphicEngine;
import game.LevelReader;
import graphics.GameGraphicElement;
import utils.Axis;
import utils.Direction;

public class ScreenBorderCollider extends BaseCollider {
    protected Direction position;

    public ScreenBorderCollider(Rectangle b, Direction position) {
        super(b);
        this.position = position;
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public ScreenBorderCollision getCollision() {
        return new ScreenBorderCollision(this);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        int displacement = m.getCollider().displaceX(collision, 0);
        m.getCollider().getEntity().getGraphicElement().translate(displacement, 0);
    }

    public void handleHorizontalCollision(Collision c) {
        horizontalCollision(c);
    }

    public void handleHorizontalCollision(PiranhaPlantCollision p) {
        horizontalCollision(p);
        GraphicEngine.instance().setDepth(p.getCollider().getEntity().getGraphicElement(), GraphicEngine.DEFAULT_DEPTH);
    }
    
    public void handleHorizontalCollision(LevelEndCollision c){
    }

    public void handleVerticalCollision(LevelEndCollision c){
    }

    protected void horizontalCollision(Collision c) {
        GraphicEngine graphicEngine = GraphicEngine.instance();
        GameGraphicElement graphicElement = c.getCollider().getEntity().getGraphicElement();

        Direction myCollisionDirection = Direction.horizontalDirectionFromSign((int) getVelocity().getXComponent());
        Direction collisionDirection = Direction.horizontalDirectionFromSign((int) c.getCollider().getVelocity().getXComponent());

        if  (myCollisionDirection == position || collisionDirection != position) {
            if (!graphicElement.added()) {
                Point colliderPosition = c.getCollider().getVelocity().getEnd();
                int heightDifference = graphicElement.getSprite().getIconHeight() - LevelReader.CHUNK;

                graphicElement.setPosition(
                    (int) (colliderPosition.getX() - graphicEngine.getPosition()),
                    (int) colliderPosition.getY() + heightDifference
                );

                graphicElement.add();
            }
        }
    }
}
