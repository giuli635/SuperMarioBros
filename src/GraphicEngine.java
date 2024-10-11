import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicEngine {
    protected static GraphicEngine uniqueInstance;
    protected Collection<GraphicElement> elements;
    protected JFrame frame;
    protected JPanel panel;

    private GraphicEngine() {
        elements = new ArrayList<>();
        frame = new JFrame();
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 300));
        frame.addWindowListener(Game.instance());
        frame.add(panel);
        frame.pack();
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
        elements.add(e);
    }

    public void removeGraphicElement(GraphicElement e) {
        elements.remove(e);
    }
}
