package game;

import java.util.Timer;
import java.util.TimerTask;

public class LevelTimer {
    protected int remainingTime;
    protected Timer timer;
    protected boolean isPaused;

    public LevelTimer(int seconds) {
        remainingTime = seconds;
        timer = new Timer();
        isPaused = false;
        startTimer();
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    protected void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTime > 0 && !isPaused) {
                    remainingTime--;
                } else if (remainingTime <= 0) {
                    stopTimer();
                }
            }
        }, 0, 1000);
    }

    public void pauseTimer() {
        isPaused = true;
    }

    public void resumeTimer() {
        isPaused = false;
    }

    public void stopTimer() {
        timer.cancel();
    }
}