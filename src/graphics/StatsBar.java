package graphics;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import game.GraphicEngine;
import game.LevelTimer;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

public class StatsBar implements GraphicElement {
    protected Font customFont;
    protected LevelTimer levelTimer;
    protected int lives;
    protected int level;
    protected  int score;
    protected JPanel mainPanel;

    public StatsBar (LevelTimer timer, int lives, int level, int score) {
        levelTimer = timer;
        this.lives = lives;
        this.level = level;
        this.score = score;
        mainPanel = new JPanel();

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/SuperMario256.ttf")).deriveFont(38f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        StatsBar thisBar = this;
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.LINE_AXIS));
                mainPanel.add(Box.createHorizontalGlue());
                addElement(new StatsBarElement(thisBar, "TIME"));
                addElement(new StatsBarElement(thisBar, "LEVEL"));
                addElement(new StatsBarElement(thisBar, "SCORE"));
                addElement(new StatsBarElement(thisBar, "LIVES"));
                mainPanel.setBounds(0,0,(int) GraphicEngine.instance().getPanelSize().getWidth(),100);
                mainPanel.setOpaque(false);
            }
        });
    }

    public void addElement(StatsBarElement element) {
        mainPanel.add(element.getPanel());
        mainPanel.add(Box.createHorizontalGlue());
    }

    public void draw() {
        // timeLabel.setText("" + levelTimer.getRemainingTime());
        // livesLabel.setText("" + lives);
    }

    @Override
    public void translate(int dx, int dy) {
    }

    @Override
    public Point getPosition() {
        return mainPanel.getBounds().getLocation();
    }

    @Override
    public void setPosition(int x, int y) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Rectangle newBounds = new Rectangle(mainPanel.getBounds());
                newBounds.setLocation(x, y);
                mainPanel.setBounds(newBounds);
            }
        });
    }

    @Override
    public JComponent getComponent() {
        return mainPanel;
    }

    public Font getFont() {
        return customFont;
    }
}
