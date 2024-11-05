package graphics;

import javax.swing.SwingUtilities;

public class TemporaryScreenOverlay extends ScreenOverlay {
    protected int timeout;

    public TemporaryScreenOverlay(String overlayText, int timeout) {
        super(overlayText);
        this.timeout = timeout;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initOverlay();
            }
        });
    }

    @Override
    public void add() {
        super.add();

        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        remove();
    }
}
