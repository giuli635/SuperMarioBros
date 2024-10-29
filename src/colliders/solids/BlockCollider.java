package colliders.solids;
import java.awt.Rectangle;

import collisions.Collision;
import collisions.solids.BlockCollision;
import entities.solids.Block;
import utils.Axis;

public class BlockCollider extends SolidCollider {
    protected Block block;

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
