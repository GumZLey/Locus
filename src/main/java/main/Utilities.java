package main;

import java.awt.Image;
import javax.swing.ImageIcon;

interface Sprite {
    static final String PATH                    = "src/main/java/main/resources/";
    static final String Auto_gun                = PATH + "Auto_gun.png";
    static final String bullet                  = PATH + "bullet.png";
    static final String enemy_big               = PATH + "enemy-big.png";
    static final String enemy_medium            = PATH + "enemy-medium.png";
    static final String enemy_small             = PATH + "enemy-small.png";
    static final String explosion               = PATH + "explosin.png";
    static final String floor_left              = PATH + "floor_left.png";
    static final String floor_right             = PATH + "floor_right.png";
    static final String floor_top               = PATH + "floor_top.png";
    static final String gun                     = PATH + "gun.png";
    static final String laser_bolts             = PATH + "laser_bolts.png";
    static final String Robots                  = PATH + "Robots.png";
    
}

interface Image_File {
    static final String PATH                    = "src/main/java/main/resources/";
    static final String space_background        = PATH + "space_background.png";
    static final String background              = PATH + "background.png";
    
    static final int FRAMEWIDTH  = 1360;
    // static final int FRAMEWIDTH  = 1024;
    static final int FRAMEHEIGHT = 768;
    
    // static final int FRAMEWIDTH  = 800;
    // static final int FRAMEHEIGHT = 600;
}

class MyImageIcon extends ImageIcon
{
    public MyImageIcon(String fname)  { super(fname); }
    public MyImageIcon(Image image)   { super(image); }

    public MyImageIcon resize(int width, int height)
    {
        Image oldimg = this.getImage();
        Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
}
