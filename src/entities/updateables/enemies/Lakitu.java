package entities.updateables.enemies;

import java.awt.Rectangle;

import game.GraphicEngine;
import game.LevelReader;
import graphics.GameGraphicElement;

import colliders.updateables.enemies.LakituCollider;

public class Lakitu extends BaseEnemy {
    protected static final int THROW_COOLDOWN = 10000;
    protected static final int MIN_DISTANCE = 100;
    protected static String SPRITES_FOLDER = "lakitu";
    protected long lastThrowTime = 0;

    protected LakituCollider collider;
    protected GameGraphicElement graphicElement;

    public Lakitu() {
        super();
        collider = new LakituCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public void recieveDamage() {
        die(SPRITES_FOLDER + "Hiding");
    }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastThrowTime >= THROW_COOLDOWN &&
                graphicElement.getPosition().getX() > MIN_DISTANCE &&
                graphicElement.getPosition().getX() < game.GraphicEngine.instance().getPanelSize().width
                        - MIN_DISTANCE) {
            throwEnemy();
            lastThrowTime = currentTime;
        }

        graphicElement.translate(speedX, 0);
        collider.translate(speedX, 0);
    }

    public void throwEnemy() {
        Spiny spiny = new Spiny();

        int spinyGraphicX = (int) graphicElement.getPosition().getX();
        int spinyGraphicY = (int) graphicElement.getPosition().getY();

        spiny.getGraphicElement().setPosition(spinyGraphicX, spinyGraphicY - LevelReader.CHUNK);

        int spinyColliderX = (int) collider.getPosition().getX();
        int spinyColliderY = (int) collider.getPosition().getY();

        spiny.getCollider().setPosition(spinyColliderX, spinyColliderY - LevelReader.CHUNK);
        spiny.getCollider().activate();

        GraphicEngine.instance().add(spiny.getGraphicElement());
        spiny.load();
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public LakituCollider getCollider() {
        return collider;
    }
}
