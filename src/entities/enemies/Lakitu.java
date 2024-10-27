package entities.enemies;

import java.awt.Rectangle;

import entities.BaseUpdatableEntity;
import entities.Entity;
import game.CollisionsEngine;
import game.Game;
import game.GraphicEngine;
import game.LevelReader;
import graphics.GameGraphicElement;
import java.util.Timer;
import java.util.TimerTask;

import colliders.enemies.LakituCollider;

public class Lakitu extends BaseUpdatableEntity implements Enemy {
    protected static final int THROW_COOLDOWN = 10000;
    protected static final int MIN_DISTANCE = 100;
    protected static String SPRITES_FOLDER = "lakitu";
    protected int speedX;
    protected long lastThrowTime = 0;

    protected LakituCollider collider;
    protected GameGraphicElement graphicElement;

    public Lakitu() {
        super();
        speedX = -2;
        collider = new LakituCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public Entity clone() {
        return new Lakitu();
    }

    @Override
    public void recieveDamage() {
        Game.instance().unregisterToUpdate(this);
        CollisionsEngine.instance().remove(collider);
        graphicElement.setSprite(SPRITES_FOLDER + "Hiding");
        graphicElement.translate(0, -9);
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run(){
                GraphicEngine.instance().remove(graphicElement);
            }
        };

        timer.schedule(task,1000);
    }

    @Override
    public int getPoints() {
        return 60;
    }

    public int subtractPoints(){
        return 0;
    }

    public void switchDirection() {
        speedX = -speedX;
    }

    @Override
    public void update() {
        graphicElement.translate(speedX, 0);
        collider.translate(speedX, 0);

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
