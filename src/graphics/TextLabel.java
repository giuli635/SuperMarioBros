package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import game.LanguageConfiguration;

public class TextLabel extends BaseGraphicElement {
    protected JLabel label;
    protected String data;
    protected Font customFont;

    public TextLabel(String s) {
        data = s;
        label = new JLabel();

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/LanaPixel.ttf")).deriveFont(38f);
            label.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String string = LanguageConfiguration.instance().get(data);
                label.setForeground(Color.WHITE);
                label.setText(string);
                int width = label.getFontMetrics(customFont).stringWidth(string);
                int height = label.getFontMetrics(customFont).getHeight();
                label.setBounds(0, 0, width, height);
                label.setOpaque(false);
            }
        });
    }

    @Override
    public void translate(int dx, int dy) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Rectangle newBounds = new Rectangle(label.getBounds());
                newBounds.translate(dx, dy);
                label.setBounds(newBounds);
            }
        });
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String string = LanguageConfiguration.instance().get(data);
                label.setText(LanguageConfiguration.instance().get(data));
                label.setText(string);
                int width = label.getFontMetrics(customFont).stringWidth(string);
                int height = label.getFontMetrics(customFont).getHeight();
                label.setBounds((int) getPosition().getX(), (int) getPosition().getY(), width, height);
            }
        });
    }

    @Override
    public JComponent getComponent() {
        return label;
    }
}
