package game;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import graphics.GameGraphicElement;
import graphics.GraphicElement;

public class GraphicEngine {
    protected static GraphicEngine uniqueInstance;
    protected JFrame frame;
    protected JLayeredPane panel;
    protected Set<GraphicElement> onScreen;
    protected int position;
    protected GraphicElement[] backgrounds;

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

        backgrounds = new GraphicElement[]{new GameGraphicElement(null, "level", "mode1"), new GameGraphicElement(null, "level", "mode1")};
        for (int i = 0; i < backgrounds.length; i++) {
            backgrounds[i].setSprite("levelBackground1");
            addGraphicElement(backgrounds[i]);
        }

        backgrounds[0].setPosition(0, 480);
        backgrounds[1].setPosition((int) backgrounds[1].getCurrentSprite().getIconWidth(), 480);
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
                javax.swing.JLabel[] panels=e.getLabels();
                if (panels != null) {
                    for (JLabel label : panels) {
                        if (label != null) {
                            panel.add(label, (Integer) onScreen.size());
                        }
                    }
                }
                else panel.add(e.getLabel(), (Integer) onScreen.size());
            }
        });
    }

    public void removeGraphicElement(GraphicElement e) {
        onScreen.remove(e);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                javax.swing.JLabel[] panels=e.getLabels();
                if (panels != null) {
                    for (JLabel label : panels) {
                        if (label != null) {
                            panel.remove(label);
                        }
                    }
                }
                else panel.remove(e.getLabel());
        }});
    }

    public Dimension getPanelSize() {
        return panel.getSize();
    }

    public void scrollScreen(int velocity) {  
        translate(-velocity);
        if (backgrounds[1].getLabel().getBounds().getMaxX() < getPanelSize().getWidth()) {
            GraphicElement aux = backgrounds[0];
            backgrounds[0].setPosition((int) backgrounds[1].getLabel().getBounds().getMaxX(), 480);
            panel.moveToBack(backgrounds[0].getLabel());
            backgrounds[0] = backgrounds[1];
            backgrounds[1] = aux;
        }

        for (GraphicElement element : onScreen) {
            element.translate(velocity, 0);
        }
    }
}
