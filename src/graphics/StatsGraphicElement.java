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
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Component;

public class StatsGraphicElement implements GraphicElement {
    protected Font customFont;
    protected LevelTimer levelTimer;
    protected int lives;
    protected int level;
    protected  int score;
    protected JLabel timeLabel;
    protected JLabel livesLabel;
    protected JLabel levelLabel;
    protected JLabel scoreLabel;
    protected JPanel timePanel;
    protected JPanel livesPanel;
    protected JPanel levelPanel;
    protected JPanel scorePanel;
    protected JPanel mainPanel;


    public StatsGraphicElement(LevelTimer timer, int lives, int level, int score) {
        levelTimer = timer;
        this.lives = lives;
        this.level = level;
        this.score = score;
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
                JLabel text = new JLabel("TIME");
                text.setFont(customFont);
                text.setAlignmentX(Component.CENTER_ALIGNMENT);
                text.setForeground(Color.WHITE);
                timeLabel.setText("" + levelTimer.getRemainingTime());
                timeLabel.setFont(customFont);
                timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                timeLabel.setForeground(Color.WHITE);
                
                timePanel.add(text);
                timePanel.add(timeLabel);
                timePanel.setLayout(new BoxLayout(timePanel,BoxLayout.PAGE_AXIS));
                timePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                timePanel.setOpaque(false);

                text = new JLabel("LIVES");
                text.setFont(customFont);
                text.setAlignmentX(Component.CENTER_ALIGNMENT);
                text.setForeground(Color.WHITE);
                livesLabel.setText("" + lives);
                livesLabel.setFont(customFont);
                livesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                livesLabel.setForeground(Color.WHITE); 

                livesPanel.add(text);
                livesPanel.add(livesLabel);
                livesPanel.setLayout(new BoxLayout(livesPanel,BoxLayout.PAGE_AXIS));
                livesPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                livesPanel.setOpaque(false);

                text = new JLabel("LEVEL");
                text.setFont(customFont);
                text.setAlignmentX(Component.CENTER_ALIGNMENT);
                text.setForeground(Color.WHITE);
                levelLabel.setText("" + level);
                levelLabel.setFont(customFont);
                levelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                levelLabel.setForeground(Color.WHITE); 

                levelPanel.add(text);
                levelPanel.add(levelLabel);
                levelPanel.setLayout(new BoxLayout(levelPanel,BoxLayout.PAGE_AXIS));
                levelPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                levelPanel.setOpaque(false);

                text = new JLabel("SCORE");
                text.setFont(customFont);
                text.setAlignmentX(Component.CENTER_ALIGNMENT);
                text.setForeground(Color.WHITE);
                scoreLabel.setText("" + score);
                scoreLabel.setFont(customFont);
                scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                scoreLabel.setForeground(Color.WHITE); 

                scorePanel.add(text);
                scorePanel.add(scoreLabel);
                scorePanel.setLayout(new BoxLayout(scorePanel,BoxLayout.PAGE_AXIS));
                scorePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                scorePanel.setOpaque(false);
                
                mainPanel.add(Box.createHorizontalGlue());
                mainPanel.add(timePanel);
                mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.LINE_AXIS));
                mainPanel.add(Box.createHorizontalGlue());
                mainPanel.add(livesPanel);
                mainPanel.add(Box.createHorizontalGlue());
                mainPanel.add(levelPanel);
                mainPanel.add(Box.createHorizontalGlue());
                mainPanel.add(scorePanel);
                mainPanel.add(Box.createHorizontalGlue());
                mainPanel.setBounds(0,0,(int) GraphicEngine.instance().getPanelSize().getWidth(),100);
                mainPanel.setOpaque(false);
            }
        });
    }

    public void draw() {
        timeLabel.setText("" + levelTimer.getRemainingTime());
        livesLabel.setText("" + lives);
    }

    @Override
    public void translate(int dx, int dy) {
    }

    @Override
    public Point getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public void setPosition(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPosition'");
    }

    @Override
    public JComponent getComponent() {
        return mainPanel;
    }
}
