package collisions;

import colliders.BlockCollider;
import colliders.BrickCollider;
import colliders.CoinCollider;
import colliders.DefaultMarioCollider;
import colliders.DeleterCollider;
import colliders.EmptyBlockCollider;
import colliders.enemies.BuzzyBeetleCollider;
import colliders.enemies.GoombaCollider;
import colliders.enemies.KoopaTroopaCollider;
import colliders.enemies.LakituCollider;
import colliders.enemies.PiranhaPlantCollider;
import colliders.enemies.SpinyCollider;
import colliders.LoaderCollider;
import colliders.PipeCollider;
import colliders.QuestionBlockCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;
import colliders.SuperMarioCollider;
import colliders.powerUp.FireFlowerCollider;
import colliders.powerUp.GreenMushroomCollider;
import colliders.powerUp.StarCollider;
import colliders.powerUp.SuperMushroomCollider;
import colliders.UnloaderCollider;

public class GoombaCollision extends BaseCollision implements EnemyCollision {
    protected GoombaCollider collider;

    public GoombaCollision(GoombaCollider c) {
        collider = c;
    }

    @Override
    public GoombaCollider getCollider() {
        return collider;
    }

    @Override
    public void collide(BlockCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(ScreenDisplacementCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(ScreenBorderCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
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
    public void collide(SuperMushroomCollider c, Axis a) {
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
    public void collide(LoaderCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(BrickCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(PipeCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(QuestionBlockCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(CoinCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(UnloaderCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(DeleterCollider c, Axis a) {
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
    public void collide(FireFlowerCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(StarCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(SuperMarioCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(DefaultMarioCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(GreenMushroomCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(EmptyBlockCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }
}
