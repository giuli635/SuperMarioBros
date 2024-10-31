package graphics;

import javax.swing.*;

import game.LanguageConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameOverScreen extends BaseGraphicElement {
    private JPanel mainPanel;
    private JLabel gameOverLabel;
    private Font customFont;

    public GameOverScreen() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(1000, 480)); 
        mainPanel.setBackground(new Color(0, 0, 0, 255)); 
        
        gameOverLabel = new JLabel(LanguageConfiguration.instance().get("gameOver"));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/LanaPixel.ttf")).deriveFont(60f);
            gameOverLabel.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        gameOverLabel.setForeground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        
        mainPanel.add(gameOverLabel, gbc);
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
}
