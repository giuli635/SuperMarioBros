package graphics;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import game.GraphicEngine;
import game.LanguageConfiguration;
import game.RankingManager;
import game.RankingManager.ScoreEntry;

public class RankingScreen extends BaseGraphicElement {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private Font customFont;

    public RankingScreen(RankingManager rankingManager) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(1000, 480));
        mainPanel.setBackground(new Color(0, 0, 0, 255));

        titleLabel = new JLabel(LanguageConfiguration.instance().get("highScore"));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/LanaPixel.ttf")).deriveFont(60f);
            titleLabel.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        titleLabel.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 0, 10, 0); 

        mainPanel.add(titleLabel, gbc);

        int row = 1;
        for (ScoreEntry entry : rankingManager.getHighScores()) {
            JLabel scoreLabel = new JLabel(row + ". " + entry.getName() + " - " + entry.getScore());
            scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            scoreLabel.setForeground(Color.WHITE);

            if (customFont != null) {
                scoreLabel.setFont(customFont.deriveFont(30f));
            }

            gbc.gridy = row++;
            mainPanel.add(scoreLabel, gbc);
        }

        mainPanel.setBounds(0, 0, 1000, 480);
        mainPanel.setVisible(true);
    }

    @Override
    public JComponent getComponent() {
        return mainPanel;
    }

    @Override
    public void draw() {
        mainPanel.repaint();
    }

    @Override
    public void translate(int dx, int dy) {
        Point p = mainPanel.getLocation();
        mainPanel.setLocation(p.x + dx, p.y + dy);
    }

    @Override
    public Point getPosition() {
        return mainPanel.getLocation();
    }

    @Override
    public void setPosition(int x, int y) {
        mainPanel.setLocation(x, y);
    }

    @Override
    public void reload() {
        titleLabel.setText(LanguageConfiguration.instance().get("highScore"));
    }

    public void showRankingScreen() {
        add();
        try {
            Thread.sleep(4000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        remove();
    }
}
