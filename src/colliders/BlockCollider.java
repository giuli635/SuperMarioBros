package colliders;
import java.awt.Point;
import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.BlockCollision;
import entities.Block;

public class BlockCollider extends SolidCollider {
    protected Block block;
    protected Rectangle bounds;
    protected Point previousPosition;
    protected boolean activated;

    public BlockCollider(Block e, Rectangle b) {
        super(b);
        block = e;
    }

    public Block getEntity() {
        return block;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public BlockCollision getCollision() {
        return new BlockCollision(this);
    }
}
