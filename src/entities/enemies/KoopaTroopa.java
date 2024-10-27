package entities.enemies;

import java.awt.Rectangle;

import colliders.enemies.KoopaTroopaCollider;
import entities.BaseUpdatableEntity;
import entities.Entity;
import graphics.GameGraphicElement;

public class KoopaTroopa extends BaseUpdatableEntity implements Enemy {
    protected static String SPRITES_FOLDER = "koopa";
    protected int speedX;
    protected boolean shell;

    protected KoopaTroopaCollider collider;
    protected GameGraphicElement graphicElement;

    public KoopaTroopa(){
        speedX = -2;
        shell = false;
        collider = new KoopaTroopaCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public void recieveDamage() {
        if (!shell) {
            graphicElement.setSprite(SPRITES_FOLDER + "Shell");
            speedX = 0;
            shell = true;
        } else {
            speedX = 0;
        }
    }

    @Override
    public int getPoints() {
        return 90;
    }
    
    public int subtractPoints(){
        return 45;
    }

    @Override
    public Entity clone() {
        return new KoopaTroopa();
    }

    public void switchDirection() {
        speedX = -speedX;
        graphicElement.flipSprite();
    }

    @Override
    public void update() {
        if (!shell) {
            //TODO : Manejar los bucles de sprites
        }
        graphicElement.translate(speedX, 0);
        collider.translate(speedX, 0);  
        graphicElement.translate(0, -3);
        collider.translate(0, -3);  
    }

    public void setShell(boolean b) {
        shell = b;
    }

    public boolean getShell() {
        return shell;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int x) {
        speedX = x;
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
