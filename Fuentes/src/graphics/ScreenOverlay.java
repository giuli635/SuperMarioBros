package graphics;

import javax.swing.*;

import game.SingletonGraphicEngine;
import game.LanguageConfiguration;

import java.awt.*;

public abstract class ScreenOverlay extends BaseGraphicElement {
    public static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
    protected JPanel panel;
    protected String text;
    protected JLabel label;
    protected Font font;
    protected Rectangle bounds;

    public ScreenOverlay(String overlayText) {
        text = overlayText;
        panel = new JPanel();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Dimension panelSize = SingletonGraphicEngine.instance().getPanelSize();
                panel.setBounds(0, 0, (int) panelSize.getWidth(), (int) panelSize.getHeight()); 
                panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
                panel.setBackground(DEFAULT_BACKGROUND_COLOR); 

                font = SingletonGraphicEngine.instance().getFont().deriveFont(60f);

                label = new JLabel(LanguageConfiguration.instance().get(text));
                label.setFont(font);
                label.setForeground(Color.WHITE);
                label.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel.add(Box.createVerticalGlue());
                panel.add(label);
                panel.setOpaque(true);
            }
        });
    }

    protected void initOverlay() {
        panel.add(Box.createVerticalGlue());
        panel.setVisible(true);
    }

    @Override
    public JComponent getComponent() {
        return panel;
    }

    @Override
    public void redraw() {
        label.setText(LanguageConfiguration.instance().get(text));
    }

    @Override
    public void reload() {
        SingletonGraphicEngine.instance().addToRedraw(this);
    }

    @Override
    public void add() {
        super.add();
        SingletonGraphicEngine.instance().setDepth(this, SingletonGraphicEngine.FRONT_DEPTH + 1);
    }
}
