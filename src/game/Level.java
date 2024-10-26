package game;
import java.awt.Image;

import graphics.StatsGraphicElement;
import graphics.MarioGraphicLives;

public class Level {
    protected int points;
    protected Image background;
    protected LevelTimer levelTimer;
    protected StatsGraphicElement timerGraphicElement;
    protected MarioGraphicLives livesGraphic;
    
    public Level(int initialTime, int initialLives) {
        levelTimer = new LevelTimer(initialTime);
        timerGraphicElement = new StatsGraphicElement(levelTimer, 3, 1, 10);
        GraphicEngine.instance().add(timerGraphicElement);
        GraphicEngine.instance();
        GraphicEngine.instance().setDepth(timerGraphicElement, GraphicEngine.FRONT_DEPTH);

        points = 0;
    }

    public void decreacedLives(){
        livesGraphic.decreaceLives();
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