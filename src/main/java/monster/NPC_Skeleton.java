package monster;

import java.util.Random;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GameWindow;

public class NPC_Skeleton extends Entity{
    private int scale = 2;
    
    public NPC_Skeleton(GameWindow gw)
    {
        super(gw);
        name = "Skeleton";

        type = 2;  // 2 meaning monster
        direction = "left";
        speed = 1;
        maxLife = 3;
        life = maxLife;

        this.width = 16 * scale;
        this.height = 27 * scale;

        solidArea.x = 2;
        solidArea.width = this.width - 2;
        solidArea.height = this.height;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;



        getSkeletonImage();
    }

    public void getSkeletonImage() {
        try {
            right1 = ImageIO.read(getClass().getResourceAsStream("skeleton/tile000.png")); 
            right2 = ImageIO.read(getClass().getResourceAsStream("skeleton/tile001.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("skeleton/tile002.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("skeleton/tile003.png"));
            up1 = right1;
            up2 = right2;
            down1 = left1;
            down2 = left2;
    } catch (Exception e){}
    

    
}
    public void setAction(){

        actionLockCounter++;

        

        if(actionLockCounter == 60)  // 1 sec before random
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50 ) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100 ) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

        
    }
}

