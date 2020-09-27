package ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class App {

    public static BufferedImage getImg(String path) {
        try {
            BufferedImage img = ImageIO.read(App.class.getResource(path));
            return img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
