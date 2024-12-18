package object;

import javax.imageio.ImageIO;

import main.GameWindow;

public class Object_Heart extends Object_head{
    GameWindow gw;

    public Object_Heart(GameWindow gw) {
        this.gw = gw;

        name = "Heart";


        try {
            image = ImageIO.read(getClass().getResourceAsStream("objects/Heart_Full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("objects/Heart_gone.png"));
            image = uTool.scaleImage(image, gw.tileSize - 1, gw.tileSize);
            image2 = uTool.scaleImage(image2, gw.tileSize - 1, gw.tileSize );
        } catch (Exception e) {

        }
    }

}
