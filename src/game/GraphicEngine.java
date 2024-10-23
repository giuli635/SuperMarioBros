package game;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;

import graphics.GameGraphicElement;
import graphics.GraphicElement;

public class GraphicEngine {
    protected static GraphicEngine uniqueInstance;
    protected JFrame frame;
    protected JLayeredPane panel;
    protected Set<GraphicElement> onScreen;
    protected int position;
    protected GraphicElement background;

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
        onScreen = new HashSet<>();
        frame = new JFrame();

        panel = new JLayeredPane();
        panel.setPreferredSize(new Dimension(1000, 480));
        panel.setLayout(null);
        panel.setDoubleBuffered(true);

        frame.addWindowListener(Game.instance());
        frame.addKeyListener(Game.instance());
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        background = new GameGraphicElement(null, "level", "mode1");
        background.setSprite("levelBackground1");
        addGraphicElement(background);
        background.setPosition(0, 480);
    }

    public static GraphicEngine instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GraphicEngine();
        }
        return uniqueInstance;
    }

    public void drawFrame() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    for (GraphicElement element : onScreen) {
                        element.draw();
                    }

                    panel.revalidate();
                    panel.paintImmediately(panel.getBounds());
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addGraphicElement(GraphicElement e) {
        onScreen.add(e);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                panel.add(e.getLabel(), (Integer) onScreen.size());
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
