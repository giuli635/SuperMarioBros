package entities.updateables.mario.actions;

import java.awt.event.KeyEvent;

import colliders.updateables.mario.MarioCollider;
import entities.updateables.FireBall;
import entities.updateables.mario.Mario;
import game.Game;
import game.GraphicEngine;
import game.LevelReader;
import graphics.GameGraphicElement;
import utils.BasePrioritizable;
import utils.KeyStatus;

public class ThrowFireBall extends BasePrioritizable implements MarioAction {
    public static final int DEFAULT_PRIORITY = 1000;
    public static final int MAX_FIRE_BALL = 2;
    public static final int COOLDOWN = 500;

    protected int throwedBalls;
    protected long lastThrow;

    public ThrowFireBall() {
        priority = DEFAULT_PRIORITY;
        lastThrow = 0;
        throwedBalls = 0;
    }

    @Override
    public void execute(Mario m) {
        if (Game.instance().getKeyStatus(KeyEvent.VK_SPACE) == KeyStatus.PRESSED) {
            long currentTime = System.currentTimeMillis();
            if (throwedBalls < MAX_FIRE_BALL && currentTime - lastThrow > COOLDOWN) {
                lastThrow = currentTime;
                createFireBall(m);
                throwedBalls++;
            }
        }
    }

    protected void createFireBall(Mario m) {
        FireBall fireBall = new FireBall(this, m);
        MarioCollider collider = m.getCollider();

        GameGraphicElement graphicElement = m.getGraphicElement();

        int fireBallGraphicX = (int) graphicElement.getPosition().getX() + LevelReader.CHUNK;
        int fireBallGraphicY = (int) graphicElement.getPosition().getY() - LevelReader.CHUNK * 3 / 2;

        fireBall.getGraphicElement().setPosition(fireBallGraphicX, fireBallGraphicY);

        int fireBallColliderX = (int) collider.getPosition().getX() + LevelReader.CHUNK;
        int fireBallColliderY = (int) collider.getPosition().getY();

        fireBall.getCollider().setPosition(fireBallColliderX, fireBallColliderY);
        fireBall.getCollider().activate();

        boolean flipped = m.getCollider().getEntity().getGraphicElement().isFlipped();

        if (flipped) {
            fireBall.setSpeedX(-fireBall.getSpeedX());
            fireBall.translate(-m.getGraphicElement().getSprite().getIconWidth(), 0);
        }

        GraphicEngine.instance().add(fireBall.getGraphicElement());
        fireBall.load();
    }

    public void increaseAmmo() {
        throwedBalls--;
    }
}
