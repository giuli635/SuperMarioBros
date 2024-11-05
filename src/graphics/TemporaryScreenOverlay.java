package graphics;

public class TemporaryScreenOverlay extends ScreenOverlay {
    protected int timeout;

    public TemporaryScreenOverlay(String overlayText, int timeout) {
        super(overlayText);
        this.timeout = timeout;
        initOverlay();
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
