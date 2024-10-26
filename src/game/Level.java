package game;
import java.awt.Image;
import java.time.Duration;

import entities.mario.Mario;
import graphics.MarioGraphicLives;
import graphics.TimerGraphicElement;

public class Level {
    protected int remainingTime;
    protected int points;
    protected Image background;
    protected LevelTimer levelTimer;
    protected TimerGraphicElement timerGraphicElement;
    protected MarioGraphicLives livesGraphic;
    protected int livesMario;
    public Level(int initialTime, int initialLives) {
        this.remainingTime = initialTime;
        livesMario = initialLives;
        this.points = 0;
        initializeGraphics();
    }

    private void initializeGraphics() {
        // Initialize timer
        levelTimer = new LevelTimer(remainingTime);
        timerGraphicElement = new TimerGraphicElement(levelTimer);
        GraphicEngine.instance().addGraphicElement(timerGraphicElement);

        // Initialize lives display
        livesGraphic = new MarioGraphicLives(livesMario);
        GraphicEngine.instance().addGraphicElement(livesGraphic);
    }

    public void decreacedLives(){
    }



   // public void pauseLevel() {
     //   levelTimer.pauseTimer();
    //}

    public void stopLevel() {
        levelTimer.stopTimer();
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int p) {
        points += p;
    }

    public void subtractPoints(int p) {
        points -= p;
    }

    public boolean isTimeUp() {
        return levelTimer.getRemainingTime() <= 0;
    }
}
