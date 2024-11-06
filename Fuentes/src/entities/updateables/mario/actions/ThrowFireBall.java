package entities.updateables.mario.actions;

import java.awt.event.KeyEvent;

import entities.updateables.FireBall;
import entities.updateables.mario.Mario;
import game.SingletonGame;
import game.LevelReader;
import game.SingletonSoundManager;
import utils.BasePrioritizable;
import utils.KeyStatus;

public class ThrowFireBall extends BasePrioritizable implements StrategyMarioAction {
    public static final int DEFAULT_PRIORITY = 1000;
    public static final int MAX_FIRE_BALL = 2;
    public static final int COOLDOWN = 500;

    protected int throwedBalls;
    protected long lastThrow;
    protected Mario mario;

    public ThrowFireBall(Mario m) {
        priority = DEFAULT_PRIORITY;
        lastThrow = 0;
        throwedBalls = 0;
        mario = m;
    }

    @Override
    public void execute() {
        if (SingletonGame.instance().getKeyStatus(KeyEvent.VK_SPACE) == KeyStatus.PRESSED) {
            long currentTime = System.currentTimeMillis();
            if (throwedBalls < MAX_FIRE_BALL && currentTime - lastThrow > COOLDOWN) {
                lastThrow = currentTime;
                createFireBall();
                throwedBalls++;
            }
        }
    }

    protected void createFireBall() {
        FireBall fireBall = new FireBall(this, mario);

        mario.spawnEntity(fireBall, 0, 0);

        fireBall.getGraphicElement().translate(
            LevelReader.CHUNK,
            LevelReader.CHUNK * 3 / 2
        );

        fireBall.getCollider().translate(LevelReader.CHUNK, 0);
        fireBall.getCollider().activate();

        if (mario.getCollider().getEntity().getGraphicElement().isFlipped()) {
            fireBall.setSpeedX(-fireBall.getSpeedX());
            fireBall.translate(-mario.getGraphicElement().getSprite().getIconWidth(), 0);
        }

        SingletonSoundManager.instance().playSound("fireball.wav");
    }

    public void increaseAmmo() {
        throwedBalls--;
    }
}
