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

public class ScoreGraphicElement implements GraphicElement {
    private JLabel scoreLabel;
    private JLabel numberLabel;
    private int scoreLevel;

    public ScoreGraphicElement(int scoreLevel) {
        this.scoreLevel = scoreLevel;
        scoreLabel = new JLabel();
        numberLabel = new JLabel();
        
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/SuperMario256.ttf")).deriveFont(38f);
            scoreLabel.setFont(customFont);
            numberLabel.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
            numberLabel.setFont(new Font("Arial", Font.BOLD, 24));
        }
        
        scoreLabel.setForeground(Color.WHITE);
        numberLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(50, 20, 300, 50);
        numberLabel.setBounds(50, 50, 300, 50);
        scoreLabel.setText("SCORE");
        updateLabel();
    }

    @Override
    public void draw() {
        updateLabel();
    }

    public int getScore(){
        return scoreLevel;
    }

    public void updateScore(int score) {
        scoreLevel=score;
    }

    private void updateLabel() {
        numberLabel.setText(String.valueOf("  " + scoreLevel));
    }

    @Override
    public JLabel[] getLabels() {
        return new JLabel[]{scoreLabel, numberLabel};
    }
    
    public void decreaceScore(int decreaded) {
        scoreLevel-=decreaded;
    }

    public void incrementScore(int increment){
        scoreLevel+=increment;
    }

    @Override
    public JLabel getLabel() {
        return scoreLabel;
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public Point getPosition() {
        return new Point(scoreLabel.getX(), scoreLabel.getY());
    }

    @Override
    public void setPosition(int x, int y) {
        scoreLabel.setLocation(x, y);
        numberLabel.setLocation(x, y + 30);
    }

    // Métodos no utilizados pero requeridos por la interfaz
    @Override
    public void translate(int dx, int dy) {}

    @Override
    public ImageIcon getCurrentSprite() {
        return null;
    }

    @Override
    public void setSprite(String s) {}

    @Override
    public void flipSprite() {}

    @Override
    public void loadSprites() {}

    @Override
    public void setFolder(String folder) {}

    @Override
    public String getFolder() {
        return null;
    }

    @Override
    public boolean isFlipped() {
        return false;
    }

    @Override
    public void setFlipped(boolean f) {}

    
}
