package graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class MarioGraphicLives implements GraphicElement {
    protected JLabel livesLabel;
    protected JLabel numberLabel;
    protected int livesMario;
    protected JPanel panel;

    public MarioGraphicLives(int LivesMario) {
        panel = new JPanel();
        livesMario = LivesMario;
        livesLabel = new JLabel();
        numberLabel = new JLabel();
        
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/SuperMario256.ttf")).deriveFont(38f);
            livesLabel.setFont(customFont);
            numberLabel.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            livesLabel.setFont(new Font("Arial", Font.BOLD, 24));
            numberLabel.setFont(new Font("Arial", Font.BOLD, 24));
        }
        
        livesLabel.setForeground(Color.BLACK);
        numberLabel.setForeground(Color.BLACK);
        livesLabel.setText("LIVES");
        numberLabel.setText(String.valueOf("  " + livesMario));
        panel.add(livesLabel);
        panel.add(numberLabel);
        panel.setBounds(450, 10, 300, 50);
        updateLabel();
    }

    @Override
    public void draw() {
        updateLabel();
    }

    protected void updateLabel() {
        numberLabel.setText(String.valueOf("  " + livesMario));
    }

    @Override
    public JLabel getComponent() {
        return livesLabel;
    }

    @Override
    public Point getPosition() {
        return new Point(livesLabel.getX(), livesLabel.getY());
    }

    @Override
    public void setPosition(int x, int y) {
        livesLabel.setLocation(x, y);
        numberLabel.setLocation(x, y + 30);
    }

    public void decreaceLives() {
        livesMario--;
    }

    @Override
    public void translate(int dx, int dy) {
        panel.setAlignmentX(dx);
        panel.setAlignmentY(dy);
    }
}
