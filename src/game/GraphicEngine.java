package game;
import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import graphics.GraphicElement;

public class GraphicEngine {
    protected static GraphicEngine uniqueInstance;
    protected JFrame frame;
    protected JPanel panel;
    protected Set<GraphicElement> onScreen;
    protected int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int x) {
        position = x;
    }

    public void translate(int dx) {
        position += dx;
    }

    private GraphicEngine() {
        frame = new JFrame();
        panel = new JPanel();
        onScreen = new HashSet<>();
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
        for (GraphicElement element : onScreen) {
            element.draw();
        }

        panel.invalidate();
        panel.validate();
        panel.repaint();
    }

    public void addGraphicElement(GraphicElement e) {
        onScreen.add(e);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                panel.add(e.getLabel());
            }
        });
    }

    public void removeGraphicElement(GraphicElement e) {
        onScreen.remove(e);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                panel.remove(e.getLabel());
            }
        });
    }

    public Dimension getPanelSize() {
        return panel.getSize();
    }

    public void scrollScreen(int velocity) {  
        translate(-velocity);
        for (GraphicElement element : onScreen) {
            element.translate(velocity, 0);
        }
    }
}
