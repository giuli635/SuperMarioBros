package collisions;
import colliders.Collider;
import colliders.DeleterCollider;
import colliders.enemies.BuzzyBeetleCollider;
import colliders.enemies.GoombaCollider;
import colliders.enemies.KoopaTroopaCollider;
import colliders.enemies.LakituCollider;
import colliders.enemies.PiranhaPlantCollider;
import colliders.enemies.SpinyCollider;
import colliders.BlockCollider;
import colliders.BrickCollider;
import colliders.CoinCollider;
import colliders.LoaderCollider;
import colliders.MarioCollider;
import colliders.PipeCollider;
import colliders.QuestionBlockCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;
import colliders.powerUp.FireFlowerCollider;
import colliders.powerUp.GreenMushroomCollider;
import colliders.powerUp.StarCollider;
import colliders.powerUp.SuperMushroomCollider;
import colliders.UnloaderCollider;

public interface Collision {
    public Collider getCollider();
    public void collide(MarioCollider c, Axis a);
    public void collide(BlockCollider c, Axis a);
    public void collide(ScreenDisplacementCollider c, Axis a);
    public void collide(ScreenBorderCollider c, Axis a);
    public void collide(GoombaCollider c, Axis a);
    public void collide(SpinyCollider c, Axis a);
    public void collide(KoopaTroopaCollider c, Axis a);
    public void collide(LakituCollider c, Axis a);
    public void collide(BuzzyBeetleCollider c, Axis a);
    public void collide(SuperMushroomCollider c, Axis a);
    public void collide(LoaderCollider c, Axis a);
    public void collide(BrickCollider c, Axis a);
    public void collide(PipeCollider c, Axis a);
    public void collide(QuestionBlockCollider c, Axis a);
    public void collide(CoinCollider c, Axis a);
    public void collide(UnloaderCollider c, Axis a);
    public void collide(DeleterCollider c, Axis a);
    public void collide(PiranhaPlantCollider c, Axis a);
    public void collide(FireFlowerCollider c, Axis a);
    public void collide(StarCollider c, Axis a);
    public void collide(GreenMushroomCollider c, Axis a);
    public boolean wasManaged();
    public void setManaged(boolean managed);
}
