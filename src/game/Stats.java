package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Stats {
    protected double remainingTime;
    protected int score;
    protected int lives;
    protected int levelNumber;
    protected long lastActualization;
    protected Timer timer;
    protected boolean timerPaused;

    protected int initialTime;
    protected int initialLives;

    protected List<LevelStatsObserver> observers;
    
    public Stats(int initialTime, int initialLives, int numberLevel, int scoreLevel) {
        this.initialTime = initialTime;
        this.initialLives = initialLives;

        remainingTime = initialTime;
        lives = initialLives;
        levelNumber = numberLevel;
        score = scoreLevel;

        timer = new Timer();
        observers = new ArrayList<>();
        timerPaused = false;

        startTimer();
    }
    
    protected void startTimer() {
        lastActualization = System.currentTimeMillis();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (remainingTime > 0 && !timerPaused) {
                    long currentActualization = System.currentTimeMillis();
                    remainingTime += (lastActualization - currentActualization) / 1000;
                    lastActualization = currentActualization;
                    notifyObserver();
                } else if (remainingTime <= 0) {
                    timer.cancel();
                }
            }
        }, 0, 1000);
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
        timerPaused = true;
    }

    public void resumeTimer(){
        timerPaused = false;
        lastActualization = System.currentTimeMillis();
    }

    public void addLives(){
        lives++;
        notifyObserver();
    }
    
    public void decreaseLives() {
        lives--;
        notifyObserver();
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
        return (int) Math.floor(remainingTime); 
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void advanceLevel() {
        levelNumber++;
        remainingTime = initialTime;
    }
    
    public void reset() {
        remainingTime = initialTime;
        lives = initialLives;
        score = 0;
        levelNumber = 0;
    }
}
