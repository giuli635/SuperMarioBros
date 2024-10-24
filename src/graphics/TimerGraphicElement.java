package graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import entities.Entity;
import game.LevelTimer;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class TimerGraphicElement implements GraphicElement {
    private JLabel timeLabel;
    private JLabel numberLabel;
    private LevelTimer levelTimer;

    public TimerGraphicElement(LevelTimer levelTimer) {
        this.levelTimer = levelTimer;
        timeLabel = new JLabel();
        numberLabel = new JLabel();
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/SuperMario256.ttf")).deriveFont(38f);
            timeLabel.setFont(customFont);
            numberLabel.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
            numberLabel.setFont(new Font("Arial", Font.BOLD, 24));
        }
        
        timeLabel.setForeground(Color.WHITE);
        numberLabel.setForeground(Color.WHITE);
        timeLabel.setBounds(800, 20, 300, 50);    
        numberLabel.setBounds(800, 50, 300, 50); 
        timeLabel.setText("TIME");
        updateLabel();
    }

    @Override
    public void draw() {
        updateLabel();
    }

    private void updateLabel() {
        numberLabel.setText(String.valueOf(" " +levelTimer.getRemainingTime()));
    }

    public JLabel[] getLabels() {
        return new JLabel[]{timeLabel, numberLabel};
    }


    @Override
    public JLabel getLabel() {
        return timeLabel;
    }

    @Override
    public void translate(int dx, int dy) {
        // No es necesario desplazar el temporizador
    }

    @Override
    public void setPosition(int x, int y) {
        // No es necesario cambiar la posición
    }

    @Override
    public void loadSprites(String mode) {

    }

    @Override
    public void setSprite(String s) {
  
    }

    @Override
    public String getFolder() {
        return null;
    }

    @Override
    public void setFolder(String folder) {

    }

    @Override
    public Entity getEntity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEntity'");
    }

    @Override
    public Point getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public ImageIcon getCurrentSprite() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentSprite'");
    }

    @Override
    public void flipSprite(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'flipSprite'");
    }
}