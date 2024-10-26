package entities;

import java.awt.Rectangle;

import colliders.KoopaTroopaCollider;
import game.Game;
import graphics.GameGraphicElement;

public class KoopaTroopa extends BaseUpdatableEntity implements Enemy {
    protected static String SPRITES_FOLDER = "koopa";
    protected boolean movingRight = true;
    protected int speedX;
    protected boolean shell;

    public KoopaTroopa(){
        speedX = 2;
        shell = false;
        collider = new KoopaTroopaCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER, Game.instance().getMode());
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPoints'");
    }

    @Override
    public Entity clone() {
        return new KoopaTroopa();
    }

    public void switchDirection() {
        movingRight = !movingRight;
    }

    @Override
    public void update() {
        if (!shell) {
            //TODO : Manejar los bucles de sprites
        }
        int moveX = movingRight ? speedX : -speedX;
        graphicElement.translate(moveX, 0);
        collider.translate(moveX, 0);  
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

    public void setVelocityX(int dx) {
        speedX += dx;
    }
}
