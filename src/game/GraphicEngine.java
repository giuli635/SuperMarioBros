package game;
import java.awt.Dimension;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import graphics.GameGraphicElement;
import graphics.GraphicElement;

public class GraphicEngine {
    protected static GraphicEngine uniqueInstance;
    public static final Integer BACKGROUND_DEPTH = 0;
    public static final Integer FRONT_DEPTH = 100;
    public static final Integer DEFAULT_DEPTH = 50;
    protected JFrame frame;
    protected static final String[] modes = {"mode1", "mode2"};
    protected int mode;
    protected JLayeredPane panel;
    protected Set<GraphicElement> onScreen;
    protected int position;
    protected GameGraphicElement[] backgrounds;

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
        frame = new JFrame("Super Mario Bros. - Comisión 02 TDP");

        mode = 0;

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
        frame.setResizable(false);

        Path spritePath = Paths.get("sprites", "questionBlock", modes[mode]);
        File spriteDir = spritePath.toFile();
        File[] spriteFiles = spriteDir.listFiles();

        frame.setIconImage(new ImageIcon(spriteFiles[0].getAbsolutePath()).getImage());
    }

    public void initBackgrounds() {
        backgrounds = new GameGraphicElement[]{new GameGraphicElement(null, "level"), new GameGraphicElement(null, "level")};
        for (int i = 0; i < backgrounds.length; i++) {
            backgrounds[i].setSprite("levelBackground1");
            add(backgrounds[i]);
            setDepth(backgrounds[i], BACKGROUND_DEPTH);
        }

        backgrounds[0].setPosition(0, 480);
        backgrounds[1].setPosition((int) backgrounds[1].getSprite().getIconWidth(), 480);
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

    public void add(GraphicElement e) {
        if (!e.added()) {
            e.setAdded(true);
            onScreen.add(e);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    e.reload(); //TODO: Rework del GraphicElement;
                    panel.add(e.getComponent(), DEFAULT_DEPTH, 0);
                }
            });
        }
    }

    public void remove(GraphicElement e) {
        if (e.added()) {
            e.setAdded(false);
            onScreen.remove(e);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    panel.remove(e.getComponent());
                }
            });
        }
    }

    public Dimension getPanelSize() {
        return panel.getSize();
    }

    public void scrollScreen(int velocity) {  
        translate(-velocity);
        if (backgrounds[1].getComponent().getBounds().getMaxX() < getPanelSize().getWidth()) {
            GameGraphicElement aux = backgrounds[0];
            backgrounds[0].setPosition((int) backgrounds[1].getComponent().getBounds().getMaxX(), 480);
            backgrounds[0] = backgrounds[1];
            backgrounds[1] = aux;
        }

        for (GraphicElement element : onScreen) {
            element.translate(velocity, 0);
        }
    }

    public void moveToBack(GraphicElement e) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                panel.moveToBack(e.getComponent());
            }
        });
    }

    public void moveToFront(GraphicElement e) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                panel.moveToFront(e.getComponent());
            }
        });
    }

    public void setDepth(GraphicElement e, Integer depth) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                panel.setLayer(e.getComponent(), depth);
            }
        });
    }

    public void reset() {
        onScreen = new HashSet<>();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                panel.removeAll();
            }
        });
        initBackgrounds();
    }

    public void nextMode() {
        mode = (mode + 1) % modes.length;
        reload();
    }

    public void reload() {
        for (GraphicElement g :  onScreen) {
            g.reload();
        }
    }

    public String getMode() {
        return modes[mode];
    }
}
