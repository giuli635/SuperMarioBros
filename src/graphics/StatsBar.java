package graphics;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import game.GraphicEngine;
import game.LevelStats;
import game.LevelStatsObserver;
import game.LevelTimer;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Component;

public class StatsBar implements GraphicElement, LevelStatsObserver {
    protected Font customFont;
    protected LevelTimer levelTimer;
    protected int lives;
    protected int level;
    protected int score;
    protected JLabel timeLabel;
    protected JLabel livesLabel;
    protected JLabel levelLabel;
    protected JLabel scoreLabel;
    protected JPanel timePanel;
    protected JPanel livesPanel;
    protected JPanel levelPanel;
    protected JPanel scorePanel;
    protected JPanel mainPanel;
    protected LevelStats levelStats;

    public StatsBar(LevelStats l) {
        levelTimer = l.getLevelTimer();
        lives = l.getLives();
        score = l.getScore();
        this.level = l.getNumberLevel();

        timeLabel = new JLabel();
        livesLabel = new JLabel();
        levelLabel = new JLabel();
        scoreLabel = new JLabel();

        levelPanel = new JPanel();
        scorePanel = new JPanel();
        timePanel = new JPanel();
        livesPanel  = new JPanel();
        
        mainPanel = new JPanel();

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/SuperMario256.ttf")).deriveFont(38f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                timePanel = createPanel("TIME", timeLabel, customFont);
                livesPanel = createPanel("LIVES", livesLabel, customFont);
                levelPanel = createPanel("LEVEL", levelLabel, customFont);
                scorePanel = createPanel("SCORE", scoreLabel, customFont);
                
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

    public JPanel createPanel(String t, JLabel stat, Font  font) { 
        JLabel text = new JLabel();
        JPanel panel = new JPanel();

        text.setText(t);
        text.setFont(customFont);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setForeground(Color.WHITE);
        stat.setFont(customFont);
        stat.setAlignmentX(Component.CENTER_ALIGNMENT);
        stat.setForeground(Color.WHITE);
                
        panel.add(text);
        panel.add(stat);
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.setOpaque(false);

        return panel;
    }

    public void addElement(JPanel element) {
        mainPanel.add(element);
        mainPanel.add(Box.createHorizontalGlue());
    }

    public void draw() {
        timeLabel.setText("" + levelTimer.getRemainingTime());
        livesLabel.setText("" + lives);
        levelLabel.setText("" + level);
        scoreLabel.setText("" + score);
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
}
