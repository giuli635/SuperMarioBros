package entities.updateables.enemies;

import java.awt.Rectangle;

import graphics.GameGraphicElement;

import colliders.updateables.enemies.BuzzyBeetleCollider;

public class BuzzyBeetle extends BaseEnemy implements ShellEnemy {
    protected static String SPRITES_FOLDER = "buzzyBeetle";
    public final static String[] ANIMATED_SPRITES = {"buzzyBeetle", "buzzyBeetleWalking"};
    protected boolean shell;
    public final static int POINTS = 30; 

    protected BuzzyBeetleCollider collider;
    protected GameGraphicElement graphicElement;

    public BuzzyBeetle() {
        super();
        animatedSprites = ANIMATED_SPRITES;
        shell = false;
        collider = new BuzzyBeetleCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public BuzzyBeetle clone() {
        return new BuzzyBeetle();
    }

    @Override
    public void recieveDamage() {
        if (!shell) {
            setSprite(SPRITES_FOLDER + "Shell");
            speedX = 0;
            shell = true;
            animatedSprites = null;
        } else {
            die(SPRITES_FOLDER + "Shell");
        }
    }

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
    public BuzzyBeetleCollider getCollider() {
        return collider;
    }

    @Override
    public int pointsToAdd() {
        return POINTS;
    }

    @Override
    public int pointsToSubtract() {
        return -(POINTS/2);
    }
}
