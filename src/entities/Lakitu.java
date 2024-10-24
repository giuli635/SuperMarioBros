package entities;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
import colliders.LakituCollider;
import game.Game;
import game.GraphicEngine;
import graphics.GameGraphicElement;

public class Lakitu extends BaseUpdatableEntity implements Enemy {
    protected static String SPRITES_FOLDER = "lakitu";
    protected boolean movingRight = true;
    protected int speedX = 1;
    protected long lastThrowTime = 0;
    protected static final int THROW_COOLDOWN = 10000;
    protected static final int MIN_DISTANCE = 100;

    public Lakitu() {
        super();
        speedX = 2;
        collider = new LakituCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER, Game.instance().getMode());
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public void getDamage() {
        throw new UnsupportedOperationException("Unimplemented method 'getDamage'");
    }

    @Override
    public int getPoints() {
        throw new UnsupportedOperationException("Unimplemented method 'getPoints'");
    }

    @Override
    public Entity clone() {
        return new Lakitu();
    }

    public void switchDirection() {
        movingRight = !movingRight;
    }

    @Override
    public void update() {
        int moveX = movingRight ? speedX : -speedX;
        graphicElement.translate(moveX, 0);
        collider.translate(moveX, 0);

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastThrowTime >= THROW_COOLDOWN &&
                graphicElement.getPosition().getX() > MIN_DISTANCE &&
                graphicElement.getPosition().getX() < game.GraphicEngine.instance().getPanelSize().width
                        - MIN_DISTANCE) {
            throwEnemy();
            lastThrowTime = currentTime;
        }
    }

    public void throwEnemy() {
        Spiny spiny = new Spiny();

        int spinyGraphicX = (int) graphicElement.getPosition().getX();
        int spinyGraphicY = (int) graphicElement.getPosition().getY();

        spiny.getGraphicElement().setPosition(spinyGraphicX, spinyGraphicY);

        int spinyColliderX = (int) collider.getPosition().getX();
        int spinyColliderY = (int) collider.getPosition().getY();

        spiny.getCollider().setPosition(spinyColliderX, spinyColliderY);

        GraphicEngine.instance().addGraphicElement(spiny.getGraphicElement());
        spiny.load();
    }

}
