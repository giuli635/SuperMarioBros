package entities.updateables.enemies;

import java.awt.Rectangle;

import colliders.updateables.enemies.KoopaTroopaCollider;
import graphics.GameGraphicElement;

public class KoopaTroopa extends BaseEnemy implements ShellEnemy {
    protected static String SPRITES_FOLDER = "koopa";
    public final static String[] ANIMATED_SPRITES = {"koopa", "koopaWalking"};
    protected boolean shell;

    protected KoopaTroopaCollider collider;
    protected GameGraphicElement graphicElement;

    public KoopaTroopa(){
        super();
        animatedSprites = ANIMATED_SPRITES;
        shell = false;
        collider = new KoopaTroopaCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public void recieveDamage() {
        if (!shell) {
            setSprite(SPRITES_FOLDER + "Shell");
            speedX = 0;
            shell = true;
        } else {
            die(SPRITES_FOLDER);
        }
    }

    @Override
    public void update() {
        if (!shell) {
            //TODO : Manejar los bucles de sprites
        }
        super.update();
    }

    public void setShell(boolean b) {
        shell = b;
    }

    public boolean getShell() {
        return shell;
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public KoopaTroopaCollider getCollider() {
        return collider;
    }
}
