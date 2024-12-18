package main;

import java.util.Random;

import entity.Entity;
import monster.NPC_Skeleton;

public class AssetSetter {
    GameWindow gw;

    public AssetSetter(GameWindow gw)
    {
        this.gw = gw;
    }

    public void setNPC()
    {


    }

    public void setMonster(int s) {

        while (gw.monster.size() <= s)
        {
            Random rand = new Random();
            int minX = 16, minY = 4,  maxX = 75, maxY = 43;

            int randx = rand.nextInt((maxX - minX) + 1) + minX;
            int randy = rand.nextInt((maxY - minY) + 1) + minY;
            Entity ske = new NPC_Skeleton(gw);

            ske.worldX = gw.tileSize * randx;
            ske.worldY = gw.tileSize * randy;
            gw.monster.add(ske);

        }
    }
}
