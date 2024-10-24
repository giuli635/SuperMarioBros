package game;

import java.util.Timer;
import java.util.TimerTask;

public class LevelTimer {
    private int remainingTime;
    private Timer timer;

    public LevelTimer(int initialTimeInSeconds) {
        this.remainingTime = initialTimeInSeconds;
        this.timer = new Timer();
        startTimer();
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    private void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTime > 0) {
                    remainingTime--;
                } else {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    public void stopTimer() {
        timer.cancel();
    }
}