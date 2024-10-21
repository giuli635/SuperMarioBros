package collisions;
import colliders.Collider;
import colliders.GameCollider;
import colliders.GoombaCollider;
import colliders.KoopaTroopaCollider;
import colliders.MarioCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;
import colliders.SpinyCollider;
import colliders.SuperMushroomCollider;

public interface Collision {
    public Collider getCollider();
    public void collide(MarioCollider c, Axis a);
    public void collide(GameCollider c, Axis a);
    public void collide(ScreenDisplacementCollider c, Axis a);
    public void collide(ScreenBorderCollider c, Axis a);
    public void collide(GoombaCollider c, Axis a);
    public void collide(SpinyCollider c, Axis a);
    public void collide(KoopaTroopaCollider c, Axis a);
    public void collide(SuperMushroomCollider c, Axis a);
}
