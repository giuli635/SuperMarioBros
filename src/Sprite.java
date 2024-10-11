import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
    protected Image image;

    public Sprite(String s) throws IOException {
        image = ImageIO.read(new File(s));
    }

    public void draw(int x, int y){

    }

    public Image getImage() {
        return image;
    }
}
