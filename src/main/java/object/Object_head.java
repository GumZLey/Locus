package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GameWindow;
import main.UtilityTool;

public class Object_head {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 16, 16);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2d, GameWindow gw) {
        int screenX = worldX - gw.player.worldX + gw.player.screenX;
        int screenY = worldY - gw.player.worldY + gw.player.screenY;

        g2d.drawImage(image, screenX, screenY, gw.tileSize, gw.tileSize, null);
        g2d.drawRect(screenX, screenY, gw.tileSize, gw.tileSize);
    }
}
