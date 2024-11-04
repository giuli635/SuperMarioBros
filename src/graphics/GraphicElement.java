package graphics;

import javax.swing.JComponent;

public interface GraphicElement {
    public JComponent getComponent();
    public void redraw();
    public void setAdded(boolean status);
    public boolean added();
    public void add();
    public void remove();
    public void reload();
}
