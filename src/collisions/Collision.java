package collisions;

import colliders.*;
import colliders.invisibles.*;
import colliders.solids.*;
import colliders.updateables.enemies.*;
import colliders.updateables.mario.*;
import colliders.updateables.powerups.*;
import utils.Axis;

public interface Collision {
    public Collider getCollider();
    public void collide(SuperMarioCollider c, Axis a);
    public void collide(DefaultMarioCollider c, Axis a);
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
    public void collide(GraphicUnloaderCollider c, Axis a);
    public void collide(DeleterCollider c, Axis a);
    public void collide(PiranhaPlantCollider c, Axis a);
    public void collide(FireFlowerCollider c, Axis a);
    public void collide(StarCollider c, Axis a);
    public void collide(GreenMushroomCollider c, Axis a);
    public void collide(EmptyBlockCollider c, Axis a);
    public void collide(LevelEndCollider c, Axis a);
    public boolean wasManaged();
    public void setManaged(boolean managed);
}
