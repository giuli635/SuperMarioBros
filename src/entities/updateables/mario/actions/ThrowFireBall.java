package entities.updateables.mario.actions;

import java.awt.event.KeyEvent;

import colliders.updateables.mario.MarioCollider;
import entities.updateables.FireBall;
import entities.updateables.mario.Mario;
import game.Game;
import game.GraphicEngine;
import game.LevelReader;
import game.LevelStats;
import graphics.GameGraphicElement;
import utils.KeyStatus;

public class ThrowFireBall extends BaseMarioAction {
    public static final int DEFAULT_PRIORITY = 1000;
    public static final int MAX_FIRE_BALL = 2;

    protected int throwedBalls;

    public ThrowFireBall() {
        priority = DEFAULT_PRIORITY;
        throwedBalls = 0;
    }

    @Override
    public void execute(Mario m) {
        if (throwedBalls < MAX_FIRE_BALL
                && Game.instance().getKeyStatus(KeyEvent.VK_SPACE) == KeyStatus.PRESSED) {
            FireBall fireBall = new FireBall(this);
            MarioCollider collider = m.getCollider();
            GameGraphicElement graphicElement = m.getGraphicElement();

            int fireBallGraphicX = (int) graphicElement.getPosition().getX() + LevelReader.CHUNK;
            int fireBallGraphicY = (int) graphicElement.getPosition().getY() - LevelReader.CHUNK * 3 / 2;

            fireBall.getGraphicElement().setPosition(fireBallGraphicX, fireBallGraphicY);

            int fireBallColliderX = (int) collider.getPosition().getX() + LevelReader.CHUNK;
            int fireBallColliderY = (int) collider.getPosition().getY();

            fireBall.getCollider().setPosition(fireBallColliderX, fireBallColliderY);
            fireBall.getCollider().activate();

            GraphicEngine.instance().add(fireBall.getGraphicElement());
            fireBall.load();

            throwedBalls++;
        }
    }

    public void increaseAmmo() {
        throwedBalls--;
    }
}
