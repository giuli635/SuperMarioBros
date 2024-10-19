package collisions;
import colliders.Collider;
import colliders.GameCollider;
import colliders.GoombaCollider;
import colliders.MarioCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;

public interface Collision {
    public void collide(MarioCollider c, Axis a);
    public void collide(GameCollider c, Axis a);
    public void collide(ScreenDisplacementCollider c, Axis a);
    public void collide(ScreenBorderCollider c, Axis a);
    public void collide(GoombaCollider c, Axis a);
    public Collider getCollider();
}
