package game;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import graphics.GameGraphicElement;
import graphics.GraphicElement;
import graphics.TranslatableGraphicElement;

public class GraphicEngine {
    protected static GraphicEngine uniqueInstance;
    public static final String FRAME_TITLE = "Super Mario Bros. - Comisión 02 TDP";
    public static final Integer BACKGROUND_DEPTH = 0;
    public static final Integer FRONT_DEPTH = 100;
    public static final Integer DEFAULT_DEPTH = 50;
    public static final int FRAME_WIDTH = 1000;
    public static final int FRAME_HEIGHT = 480;
    public static final String fontPath = "font/LanaPixel.ttf";
    protected static final String[] modes = {"mode1", "mode2"};

    protected int mode;
    protected int position;
    protected JFrame frame;
    protected JLayeredPane panel;
    protected GameGraphicElement[] backgrounds;
    protected Set<GraphicElement> staticElementsOnScreen;
    protected Set<TranslatableGraphicElement> dynamicElementsOnScreen;
    protected List<GraphicElement> toRedraw;
    protected Font font;

    private GraphicEngine() {
        staticElementsOnScreen = new HashSet<>();
        dynamicElementsOnScreen = new HashSet<>();
        toRedraw = new ArrayList<>();

        mode = 0;

        panel = new JLayeredPane();
        panel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        panel.setLayout(null);
        panel.setDoubleBuffered(true);

        initFrame();
        loadFont();
    }

    protected void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.BOLD);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void initFrame() {
        frame = new JFrame(FRAME_TITLE);
        frame.addWindowListener(Game.instance());
        frame.addKeyListener(Game.instance());

        frame.getContentPane().add(panel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        File spriteDir = Paths.get("sprites", "questionBlock", modes[mode]).toFile();
        File[] spriteFiles = spriteDir.listFiles();

        frame.setIconImage(new ImageIcon(spriteFiles[0].getAbsolutePath()).getImage());
    }

    public void initBackgrounds() {
        backgrounds = new GameGraphicElement[]{new GameGraphicElement(null, "level"), new GameGraphicElement(null, "level")};
        for (int i = 0; i < backgrounds.length; i++) {
            backgrounds[i].setSprite("levelBackground1");
            backgrounds[i].add();
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
                    List<GraphicElement> toRedrawCopy = toRedraw;
                    toRedraw = new ArrayList<>();
                    for (GraphicElement element : toRedrawCopy) {
                        element.redraw();
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
        add(e, staticElementsOnScreen);
    }

    public void remove(GraphicElement e) {
        remove(e, staticElementsOnScreen);
    }

    public void add(TranslatableGraphicElement e) {
        add(e, dynamicElementsOnScreen);
    }

    public void remove(TranslatableGraphicElement e) {
        remove(e, dynamicElementsOnScreen);
    }

    protected <T extends GraphicElement> void add(T e, Set<T> set) {
        if (!e.added()) {
            e.setAdded(true);
            set.add(e);
            e.reload();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    panel.add(e.getComponent(), DEFAULT_DEPTH, 0);
                }
            });
        }
    }

    protected <T extends GraphicElement> void remove(T e, Set<T> set) {
        if (e.added()) {
            e.setAdded(false);
            set.remove(e);
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int x) {
        position = x;
    }

    public void translate(int dx) {
        position += dx;
    }

    public void scrollScreen(int velocity) {  
        translate(-velocity);
        if (backgrounds[1].getComponent().getBounds().getMaxX() < getPanelSize().getWidth()) {
            GameGraphicElement aux = backgrounds[0];
            backgrounds[0].setPosition((int) backgrounds[1].getComponent().getBounds().getMaxX(), 480);
            backgrounds[0] = backgrounds[1];
            backgrounds[1] = aux;
        }

        for (TranslatableGraphicElement element : dynamicElementsOnScreen) {
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
        staticElementsOnScreen = new HashSet<>();
        dynamicElementsOnScreen = new HashSet<>();
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
        for (GraphicElement g : staticElementsOnScreen) {
            g.reload();
        }

        for (GraphicElement g : dynamicElementsOnScreen) {
            g.reload();
        }
    }

    public String getMode() {
        return modes[mode];
    }

    public void addToRedraw(GraphicElement graphicElement) {
        toRedraw.add(graphicElement);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void focusFrame() {
        frame.requestFocus();
    }
}
