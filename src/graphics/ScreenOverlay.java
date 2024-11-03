package graphics;

import javax.swing.*;

import game.GraphicEngine;
import game.LanguageConfiguration;
import game.SoundManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ScreenOverlay extends BaseGraphicElement {
    protected JPanel panel;
    protected String text;
    protected JLabel label;
    protected Font customFont;
    protected static final int DISPLAY_DURATION_MS = 3000;
    protected boolean isDisplayed = false;


    public ScreenOverlay(String s) {
        text = s;
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(1000, 480)); 
        panel.setBackground(new Color(0, 0, 0, 255)); 
        
        label = new JLabel(LanguageConfiguration.instance().get(text));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/LanaPixel.ttf")).deriveFont(60f);
            label.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        label.setForeground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        
        panel.add(label, gbc);
        panel.setBounds(0, 0, 1000, 480); 
        panel.setVisible(true);
    }

    public void showOverlay(GraphicEngine graphicEngine, SoundManager soundManager) {
        if (!isDisplayed) {
            add();
            graphicEngine.setDepth(this, GraphicEngine.FRONT_DEPTH + 1);
            soundManager.playSound("gameover.wav");
            isDisplayed = true;
            try {
                Thread.sleep(DISPLAY_DURATION_MS); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            remove();
            isDisplayed = false;
        }
    }


    @Override
    public JComponent getComponent() {
        return panel;
    }

    @Override
    public void draw() {
        panel.repaint();
    }

    @Override
    public void translate(int dx, int dy) {
        Point p = panel.getLocation();
        panel.setLocation(p.x + dx, p.y + dy);
    }

    @Override
    public Point getPosition() {
        return panel.getLocation();
    }

    @Override
    public void setPosition(int x, int y) {
        panel.setLocation(x, y);
    }

    @Override
    public void reload() {
        label.setText(LanguageConfiguration.instance().get(text));
    }
}
