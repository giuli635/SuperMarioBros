package graphics;

import java.util.Timer;
import java.util.TimerTask;

public class TemporaryScreenOverlay extends ScreenOverlay {
    protected int timeout;

    public TemporaryScreenOverlay(String overlayText, int timeout) {
        super(overlayText);
        this.timeout = timeout;
    }

    @Override
    public void add() {
        super.add();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                remove();
            }
        }, timeout);
    }
}
