package graphics;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import game.GraphicEngine;
import game.LanguageConfiguration;
import game.Stats;
import game.StatsPanel;
import game.LevelStatsObserver;
import game.LevelTimer;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.awt.Component;

public class StatsBar extends BaseGraphicElement implements LevelStatsObserver {
    protected Font customFont;
    protected LevelTimer levelTimer;
    protected int lives;
    protected int level;
    protected int score;

    protected JLabel timeLabel;
    protected JLabel livesLabel;
    protected JLabel levelLabel;
    protected JLabel scoreLabel;

    protected StatsPanel timePanel;
    protected StatsPanel livesPanel;
    protected StatsPanel levelPanel;
    protected StatsPanel scorePanel;
    protected JPanel mainPanel;
    protected Stats levelStats;

    public StatsBar(Stats l) {
        levelStats=l;
        levelTimer = l.getLevelTimer();
        lives = l.getLives();
        score = l.getScore();
        this.level = l.getLevelNumber();
        l.addObserver(this);
        
        mainPanel = new JPanel();

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/LanaPixel.ttf")).deriveFont(38f);
            customFont = customFont.deriveFont(Font.BOLD);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                timePanel = createPanel(LanguageConfiguration.instance().get("time"), levelTimer.getRemainingTime(), customFont);
                livesPanel = createPanel(LanguageConfiguration.instance().get("lives"), lives, customFont);
                levelPanel = createPanel(LanguageConfiguration.instance().get("level"), level, customFont);
                scorePanel = createPanel(LanguageConfiguration.instance().get("score"), score, customFont);
                
                mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.LINE_AXIS));
                mainPanel.setBounds(0,0,(int) GraphicEngine.instance().getPanelSize().getWidth(),100);
                mainPanel.setOpaque(false);
                mainPanel.add(Box.createHorizontalGlue());
                addElement(timePanel);
                addElement(livesPanel);
                addElement(levelPanel);
                addElement(scorePanel);
            }
        });
    }

    public StatsPanel createPanel(String t, int i, Font  font) { 
        StatsPanel panel = new StatsPanel(t, i, font);

        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.setOpaque(false);

        return panel;
    }

    public void addElement(StatsPanel element) {
        mainPanel.add(element);
        mainPanel.add(Box.createHorizontalGlue());
    }

    public void draw() {
        timePanel.setStat(levelTimer.getRemainingTime());
        livesPanel.setStat(lives);
        levelPanel.setStat(level);
        scorePanel.setStat(score);
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

    @Override
    public void onStatsChanged() {
        lives = levelStats.getLives();
        score = levelStats.getScore();
    }

    @Override
    public void reload() {
        timePanel.setText(LanguageConfiguration.instance().get("time"));
        livesPanel.setText(LanguageConfiguration.instance().get("lives"));
        levelPanel.setText(LanguageConfiguration.instance().get("level"));
        scorePanel.setText(LanguageConfiguration.instance().get("score"));
    }
}
