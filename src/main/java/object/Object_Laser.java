package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Projectile;
import main.GameWindow;

public class Object_Laser extends Projectile{

    GameWindow gw;

    public Object_Laser(GameWindow gw) {
        super(gw);

        name = "Laser";
        speed = 5;
        attack = 2;
        alive = false;
        maxLife = 80;
        life = maxLife;
        getImage();
    }

    public void getImage()
    {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("objects/laser.png"));
            down1 = up1;
            left1 = ImageIO.read(getClass().getResourceAsStream("objects/laser_side.png"));
            right1 = left1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
