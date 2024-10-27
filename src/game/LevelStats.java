package game;

import java.util.ArrayList;
import java.util.List;

public class LevelStats {
    protected int remainingTime;
    protected int score;
    protected int lives;
    protected int levelNumber;
    protected LevelTimer levelTimer;
    protected List<LevelStatsObserver> observers;
    
    public LevelStats(int initialTime, int initialLives, int numberLevel) {
        remainingTime = initialTime;
        lives = initialLives;
        score = 0;
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
    
    public void decreaseLives() {
        lives--;
        notifyObserver();
    }
    
    public void addPoints(int points) {
        score += points;
        notifyObserver();
    }

    public void subtractPoints(int p) {
        if (score >= p){
            score -= p;
            notifyObserver();
        }
        else{
            score = 0;
            notifyObserver();
        }
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