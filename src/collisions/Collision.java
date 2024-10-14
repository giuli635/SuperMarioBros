package collisions;
import colliders.Direction;
import colliders.GameCollider;
import colliders.MarioCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;

public interface Collision {
    public void collide(MarioCollider c, Direction d);
    public void collide(GameCollider c, Direction d);
    public void collide(ScreenDisplacementCollider c, Direction d);
    public void collide(ScreenBorderCollider c, Direction d);
}
