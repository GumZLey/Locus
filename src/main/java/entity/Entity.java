package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GameWindow;

public class Entity {
    GameWindow gw;

    public String name;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle solidArea = new Rectangle(0, 0, 16, 16);
    public boolean collisionOn = false;

    public int width, height;
    public int actionLockCounter;

    public int maxLife;
    public int life;

    public boolean invincible = false;
    public int invincibleCounter = 0;

    public int type;
    public boolean attacking = false;
    public int attack;
    public Projectile projectile;
    public boolean alive;

    public Entity(GameWindow gw) {
        this.gw = gw;
        this.width = gw.tileSize;
        this.height = gw.tileSize;
    }

    public void setAction() {}
    public void update() {
        setAction();

        collisionOn = false;
        gw.hitbox_checker.checkTile(this);
        gw.hitbox_checker.checkObject(this, false);
        gw.hitbox_checker.checkEntity(this, gw.monster);
        boolean contactPlayer = gw.hitbox_checker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            if(gw.player.invincible == false)
            {
                gw.player.life -= 1;
                gw.player.invincible = true;
            }
        }

        for (int i = 0; i < gw.monster.size(); i++) {
            if (gw.monster.get(i).life <= 0)
            {
                gw.ui.score++;
            }
        }

        if (!collisionOn)
            {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    default:
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10)
            {
                if (spriteNum == 1) {spriteNum = 2;}
                else if (spriteNum == 2) {spriteNum = 1;}
                spriteCounter = 0;
            }
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;;
        int screenX = worldX - gw.player.worldX + gw.player.screenX;
        int screenY = worldY - gw.player.worldY + gw.player.screenY;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {image = up1;}
                if (spriteNum == 2) {image = up2;}
                break;
            case "down":
                if (spriteNum == 1) {image = down1;}
                if (spriteNum == 2) {image = down2;}
                break;
            case "left":
                if (spriteNum == 1) {image = left1;}
                if (spriteNum == 2) {image = left2;}
                break;
            case "right":
                if (spriteNum == 1) {image = right1;}
                if (spriteNum == 2) {image = right2;}
                break;
            default:
                break;
        }

        g2d.drawImage(image, screenX, screenY, width, height, null);
    }

}
