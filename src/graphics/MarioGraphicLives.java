package graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import entities.Entity;
import entities.mario.Mario;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class MarioGraphicLives implements GraphicElement {
    private JLabel livesLabel;
    private JLabel numberLabel;
    private int livesMario;

    public MarioGraphicLives(int LivesMario) {
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

        livesLabel.setForeground(Color.WHITE);
        numberLabel.setForeground(Color.WHITE);
        livesLabel.setBounds(850, 20, 300, 50);
        numberLabel.setBounds(850, 50, 300, 50);
        livesLabel.setText("LIVES");
        updateLabel();
    }

    @Override
    public void draw() {
        updateLabel();
    }

    private void updateLabel() {
        numberLabel.setText(String.valueOf("  " + livesMario));
    }

    public int getLivesMario(){
        return livesMario;
    }

    public void updateLives(int lives) {
        livesMario = lives;
    }

    @Override
    public JLabel[] getLabels() {
        return new JLabel[] { livesLabel, numberLabel };
    }

    @Override
    public JLabel getLabel() {
        return livesLabel;
    }

    @Override
    public Entity getEntity() {
        return null;
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

    // Métodos no utilizados pero requeridos por la interfaz
    @Override
    public void translate(int dx, int dy) {
    }

    @Override
    public ImageIcon getCurrentSprite() {
        return null;
    }

    @Override
    public void setSprite(String s) {
    }

    @Override
    public void flipSprite() {
    }

    @Override
    public void setFolder(String folder) {
    }

    @Override
    public String getFolder() {
        return null;
    }

    @Override
    public boolean isFlipped() {
        return false;
    }

    @Override
    public void setFlipped(boolean f) {
    }

    @Override
    public void loadSprites() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadSprites'");
    }

}
