package collisions;
import colliders.Collider;
import colliders.GoombaCollider;
import colliders.BlockCollider;
import colliders.BrickCollider;
import colliders.KoopaTroopaCollider;
import colliders.LoaderCollider;
import colliders.MarioCollider;
import colliders.PipeCollider;
import colliders.QuestionBlockCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;
import colliders.SpinyCollider;
import colliders.SuperMushroomCollider;

public interface Collision {
    public Collider getCollider();
    public void collide(MarioCollider c, Axis a);
    public void collide(BlockCollider c, Axis a);
    public void collide(ScreenDisplacementCollider c, Axis a);
    public void collide(ScreenBorderCollider c, Axis a);
    public void collide(GoombaCollider c, Axis a);
    public void collide(SpinyCollider c, Axis a);
    public void collide(KoopaTroopaCollider c, Axis a);
    public void collide(SuperMushroomCollider c, Axis a);
    public void collide(LoaderCollider c, Axis a);
    public void collide(BrickCollider c, Axis a);
    public void collide(PipeCollider c, Axis a);
    public void collide(QuestionBlockCollider c, Axis a);
}
