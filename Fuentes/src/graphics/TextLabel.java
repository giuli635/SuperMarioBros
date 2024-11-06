package graphics;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;

import game.SingletonGraphicEngine;
import game.LanguageConfiguration;

public class TextLabel extends BaseTranslatableGraphicElement {
    protected String data;
    protected Font font;

    public TextLabel(String s) {
        data = s;
        label = new JLabel();

        font = SingletonGraphicEngine.instance().getFont().deriveFont(38f);
        label.setFont(font);

        label.setForeground(Color.WHITE);
        redraw();
    }

    @Override
    public void redraw() {
        String string = LanguageConfiguration.instance().get(data);
        label.setText(string);
        int width = label.getFontMetrics(font).stringWidth(string);
        int height = label.getFontMetrics(font).getHeight();
        bounds.setSize(width, height);
        super.redraw();
    }

    @Override
    public void reload() {
        SingletonGraphicEngine.instance().addToRedraw(this);
    }

    @Override
    public JComponent getComponent() {
        return label;
    }
}
