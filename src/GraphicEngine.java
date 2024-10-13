import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicEngine {
    protected static GraphicEngine uniqueInstance;
    protected JFrame frame;
    protected JPanel panel;

    private GraphicEngine() {
        frame = new JFrame();
        panel = new JPanel();
        frame.addWindowListener(Game.instance());
        frame.addKeyListener(Game.instance());
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1000, 500));
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public static GraphicEngine instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GraphicEngine();
        }
        return uniqueInstance;
    }

    public void drawFrame() {
        panel.validate();
        panel.repaint();
    }

    public void addGraphicElement(GraphicElement e) {
        panel.add(e.getLabel());
    }

    public void removeGraphicElement(GraphicElement e) {
        panel.remove(e.getLabel());
    }

    public Dimension getPanelSize() {
        return panel.getSize();
    }
}
