package entities.updateables;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entities.Body;

public class ColorAnimator implements Animator {
    protected Body entity;
    protected CyclicIterator<Map<Color, Color>> colorsIterator;
    protected Map<Color, Color> currentColors;
    protected boolean wasFlipped;

    public ColorAnimator(List<Map<Color, Color>> colorMappings, int framesPerColor, Body e) {
        List<Map<Color, Color>> mapping = new ArrayList<>(colorMappings);
        mapping.add(0, null);
        colorsIterator = new CyclicIterator<>(colorMappings, framesPerColor);
        entity = e;
    }

    public void animate() {
        Map<Color, Color> nextColors = colorsIterator.next();
        if (nextColors != null) {
            entity.getGraphicElement().removeColorRemap();
            entity.getGraphicElement().setColorRemap(nextColors);
            currentColors = nextColors;
        } else {
            entity.getGraphicElement().removeColorRemap();
        }
    }
}
