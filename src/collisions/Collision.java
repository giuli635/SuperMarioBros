package collisions;
import colliders.Collider;
import colliders.GameCollider;
import colliders.GoombaCollider;
import colliders.MarioCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;

public interface Collision {
    public void collide(MarioCollider c);
    public void collide(GameCollider c);
    public void collide(ScreenDisplacementCollider c);
    public void collide(ScreenBorderCollider c);
    public void collide(GoombaCollider c);
    public Collider getCollider();
}
