package game;

import java.util.Timer;
import java.util.TimerTask;

public class LevelTimer {
    protected int remainingTime;
    protected Timer timer;

    public LevelTimer(int seconds) {
        remainingTime = seconds;
        timer = new Timer();
        startTimer();
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    protected void startTimer() {
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