package game;

import java.util.ArrayList;
import java.util.List;

public class LevelStats {
    private int remainingTime;
    private int score;
    private int lives;
    private int numberLevel;
    private LevelTimer levelTimer;
    private List<LevelStatsObserver> observers;
    
    public LevelStats(int initialTime, int initialLives, int numberLevel) {
        this.remainingTime = initialTime;
        this.lives = initialLives;
        this.score = 0;
        this.numberLevel = numberLevel;
        this.levelTimer = new LevelTimer(remainingTime);
        this.observers = new ArrayList<>();
    }
    
    public void addObserver(LevelStatsObserver observer) {
        observers.add(observer);
    }
    
    private void notifyObservers() {
        for(LevelStatsObserver observer : observers) {
            observer.onStatsChanged();
        }
    }
    
    public void decreasedLives() {
        lives--;
        notifyObservers();
    }
    
    public void addPoints(int points) {
        score += points;
        notifyObservers();
    }

    public void subtractPoints(int p) {
        score -= p;
        notifyObservers();
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
    
    public int getNumberLevel(){
        return numberLevel;
    }

}