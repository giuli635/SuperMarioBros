package entities.updateables.enemies;

import java.awt.Rectangle;

import game.LevelReader;
import graphics.GameGraphicElement;

import colliders.updateables.enemies.LakituCollider;
import entities.updateables.UpdateableEntity;

public class Lakitu extends BaseEnemy {
    protected static final int THROW_COOLDOWN = 10000;
    public final static int POINTS = 60;
    protected static String SPRITES_FOLDER = "lakitu";
    protected long lastThrowTime = 0;

    protected LakituCollider collider;
    protected GameGraphicElement graphicElement;

    public Lakitu() {
        super();
        speedY = 0;
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
        if (currentTime - lastThrowTime >= THROW_COOLDOWN) {
            spawnEntity(new Spiny(), 0, -LevelReader.CHUNK);
            lastThrowTime = currentTime;
        }

        translate((int) speedX, (int) speedY);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public LakituCollider getCollider() {
        return collider;
    }

    @Override
    public int pointsToAdd() {
        return POINTS;
    }

    @Override
    public int pointsToSubtract() {
        return 0;
    }

    @Override
    public void spawnEntity(UpdateableEntity e, int diffX, int diffY) {
        super.spawnEntity(e, diffX, diffY);
        e.getGraphicElement().translate(0, - LevelReader.CHUNK/2);
    }
}
