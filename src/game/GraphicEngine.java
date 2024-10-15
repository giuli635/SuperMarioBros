package game;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import graphics.GraphicElement;

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
        panel.invalidate();
        panel.validate();
        panel.repaint();
    }

    public void addGraphicElement(GraphicElement e) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                panel.add(e.getLabel());
            }
        });
    }

    public void removeGraphicElement(GraphicElement e) {
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                for (Component component : panel.getComponents()) {
                    Rectangle componentBounds = component.getBounds();
                    componentBounds.translate(-velocity, 0);
                    component.setBounds(componentBounds);
                }
            }
        });
    }
}
