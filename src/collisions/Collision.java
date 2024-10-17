package collisions;
import colliders.Collider;
import colliders.Direction;
import colliders.GameCollider;
import colliders.GoombaCollider;
import colliders.MarioCollider;
import colliders.PowerUpCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;

public interface Collision {
    public void collide(MarioCollider c);
    public void collide(GameCollider c);
    public void collide(ScreenDisplacementCollider c);
    public void collide(ScreenBorderCollider c);
    public void collide(GoombaCollider c);
    public void collide(PowerUpCollider c);
    public Collider getCollider();
}
