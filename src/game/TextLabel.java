package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import graphics.BaseGraphicElement;

public class TextLabel extends BaseGraphicElement{
    protected JLabel label;
    protected String data;

    public TextLabel(String s) {
        data = s;
        label = new JLabel(LanguageConfiguration.instance().get(data));
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/LanaPixel.ttf")).deriveFont(38f);
            label.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.WHITE);
    }

    @Override
    public void translate(int dx, int dy) {
    }

    @Override
    public Point getPosition() {
        return label.getBounds().getLocation();
    }

    @Override
    public void setPosition(int x, int y) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Rectangle newBounds = new Rectangle(label.getBounds());
                newBounds.setLocation(x, y);
                label.setBounds(newBounds);
            }
        });
    }

    @Override
    public void draw() {
        label.repaint();
    }

    @Override
    public void reload() {
        label.setText(LanguageConfiguration.instance().get(data));
    }

    @Override
    public JComponent getComponent() {
        return label;
    }
}
