package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GameWindow;
import main.KeyHandler;
import object.Object_Laser;

public class Player extends Entity {
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;


    public Player(GameWindow  gw, KeyHandler keyH)
    {
        super(gw);
        this.keyH = keyH;

        screenX =  gw.screenWidth / 2 - ( gw.tileSize / 2 );
        screenY =  gw.screenHeight / 2  - ( gw.tileSize / 2 );

        solidArea = new Rectangle(4, 4, 12, 12); // set player hitbox
        
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValue();
        getPlayerImage();
    }

    public void setDefaultValue() {
        worldX =  gw.tileSize * 41;
        worldY =   gw.tileSize * 22;
        speed = 4;
        direction = "down";

        maxLife = 4;
        life = maxLife;

        projectile = new Object_Laser(gw);
    }

    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("player/player_1.png")); 
            down2 = ImageIO.read(getClass().getResourceAsStream("player/player_2.png")); 
            up1 = ImageIO.read(getClass().getResourceAsStream("player/player_3.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("player/player_4.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("player/player_1.png")); 
            right2 = ImageIO.read(getClass().getResourceAsStream("player/player_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("player/player_3.png")); 
            left2 = ImageIO.read(getClass().getResourceAsStream("player/player_4.png")); 
        } catch (Exception e){}
    }

    public void update()
    {
        if (keyH.upPressed == true || keyH.downPressed== true ||keyH.leftPressed == true || keyH.rightPressed == true)
        {
            if (keyH.upPressed == true) {
                direction = "up";
            }
            else if (keyH.downPressed == true) {
                direction = "down";
            }
            else if (keyH.leftPressed == true) {
                direction = "left";
            }
            else if (keyH.rightPressed == true) {
                direction = "right";
            }

            // Check Tile Collision
            collisionOn = false;
            gw.hitbox_checker.checkTile(this);

            // Check Object Collision
            int objIndex = gw.hitbox_checker.checkObject(this, true);
            pickUpObject(objIndex);

            
            int monsterIndex = gw.hitbox_checker.checkEntity(this, gw.monster);
            contactMonster(monsterIndex);

            if (life <= 0) { gw.gameState = gw.pauseState;}

            gw.eHandler.checkEvent();

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
        if (gw.keyH.shotKeyPressed == true)
        {
            projectile.set(worldX, worldY, direction, true, this);

            gw.projectileList.add(projectile);
        }
        

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60)
            {
                invincible = false;
                invincibleCounter = 0;                
            }
        }
    }


    public void pickUpObject(int i)
    {

    }

    public void contactMonster(int i){
        if (i != -1){
            if (invincible == false)
            {
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {
        if (i != -1)
        {
            gw.monster.get(i).life -= 1;

            if (gw.monster.get(i).life <= 0)
            {
                gw.ui.score++;
                gw.monster.remove(i);
            }
        }
    }

    public void interactNPC(int i) {
        if (i != -1) {
            System.out.println("HITT");
        }
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

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

        if (invincible == true)
        {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2d.drawImage(image, screenX, screenY,  gw.tileSize,  gw.tileSize, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
