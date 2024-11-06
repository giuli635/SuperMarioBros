package colliders.invisibles;

import java.awt.Point;
import java.awt.Rectangle;

import colliders.BaseCollider;
import collisions.VisitorCollision;
import collisions.invisibles.EmptyBlockCollision;
import collisions.invisibles.LevelEndCollision;
import collisions.invisibles.ScreenBorderCollision;
import collisions.updateables.enemies.PiranhaPlantCollision;
import collisions.updateables.mario.MarioCollision;
import entities.Entity;
import game.SingletonGraphicEngine;
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
    public void recieveCollision(VisitorCollision c, Axis a) {
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

    public void handleHorizontalCollision(VisitorCollision c) {
        horizontalCollision(c);
    }

    protected void horizontalCollision(VisitorCollision c) {
        SingletonGraphicEngine graphicEngine = SingletonGraphicEngine.instance();
        GameGraphicElement graphicElement = c.getCollider().getEntity().getGraphicElement();

        Direction myCollisionDirection = Direction.horizontalDirectionFromSign(
            (int) getVelocity().getXComponent()
        );

        Direction collisionDirection = Direction.horizontalDirectionFromSign(
            (int) c.getCollider().getVelocity().getXComponent()
        );

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

    public void handleHorizontalCollision(PiranhaPlantCollision p) {
        horizontalCollision(p);
    }

    public void handleVerticalCollision(PiranhaPlantCollision p) {
        horizontalCollision(p);
    }

    public void handleHorizontalCollision(LevelEndCollision c){
    }

    public void handleVerticalCollision(LevelEndCollision c){
    }

    public void handleHorizontalCollision(EmptyBlockCollision c) {
    }

    public void handleVerticalCollision(EmptyBlockCollision c) {
    }
}
