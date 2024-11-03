package collisions.updateables.mario;

import colliders.*;
import colliders.invisibles.*;
import colliders.solids.*;
import colliders.updateables.enemies.*;
import colliders.updateables.mario.*;
import colliders.updateables.powerups.*;
import collisions.BaseCollision;
import utils.Axis;

public class InvulnerableCollision extends BaseCollision implements MarioCollision {
    protected MarioCollision baseCollision;
    protected MarioCollider collider;

    public InvulnerableCollision(MarioCollider c, MarioCollision m) {
        baseCollision = m;
        collider = c;
    }

    @Override
    public MarioCollider getCollider() {
        return collider;
    }

    @Override
    public void setCollider(MarioCollider m) {
        collider = m;
    }
    
    @Override
    public void collide(BlockCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(ScreenDisplacementCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(ScreenBorderCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(SuperMushroomCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(LoaderCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(BrickCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(PipeCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(QuestionBlockCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(CoinCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(GraphicUnloaderCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(DeleterCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(FireFlowerCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(StarMarioCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(SuperMarioCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(DefaultMarioCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(GreenMushroomCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(EmptyBlockCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(LevelEndCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(InvulnerableCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(FireMarioCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(StarCollider c, Axis a) {
        baseCollision.setCollider(collider);
        baseCollision.collide(c, a);
    }

    @Override
    public void collide(SpinyCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(KoopaTroopaCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(LakituCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }
    @Override
    public void collide(BuzzyBeetleCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    public void collide(PiranhaPlantCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(GoombaCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(FlagPoleCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(FireBallCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(ModeSwitcherCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(LanguageSwitcherCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(RankingShowCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }
}
