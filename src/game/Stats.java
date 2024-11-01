package game;

import java.util.ArrayList;
import java.util.List;

public class Stats {
    protected int remainingTime;
    protected int score;
    protected int lives;
    protected int levelNumber;
    protected LevelTimer levelTimer;
    protected List<LevelStatsObserver> observers;
    
    public Stats(int initialTime, int initialLives, int numberLevel, int scoreLevel) {
        remainingTime = initialTime;
        lives = initialLives;
        score = scoreLevel;
        levelNumber = numberLevel;
        levelTimer = new LevelTimer(remainingTime);
        observers = new ArrayList<>();
    }
    
    public void addObserver(LevelStatsObserver observer) {
        observers.add(observer);
    }
    
    protected void notifyObserver() {
        for(LevelStatsObserver observer : observers) {
            observer.onStatsChanged();
        }
    }

    public void pauseTimer(){
        levelTimer.pauseTimer();
    }

    public void resumeTimer(){
        levelTimer.resumeTimer();
    }
    
    public void decreaseLives() {
        lives--;
        checkGameOver();
        notifyObserver();
    }

    public void checkGameOver(){
        if (lives == 0){
            notifyObserver();
        }
    }

    public void modifyPoints(int points){
        score += points;
        if (score < 0){
            score = 0;
        }
        notifyObserver();
    }

    public int getLives() { 
        return lives;
    }

    public int getScore() { 
        return score;
    }

    public int getRemainingTime() { 
        return levelTimer.getRemainingTime(); 
    }

    public LevelTimer getLevelTimer(){
        return levelTimer;
    }
    
    public int getLevelNumber(){
        return levelNumber;
    }
}
