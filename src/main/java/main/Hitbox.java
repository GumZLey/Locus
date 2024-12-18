package main;

import java.util.ArrayList;

import entity.Entity;

public class Hitbox {
    GameWindow gw;


    public Hitbox(GameWindow gw) {
        this.gw = gw;
    }

    public void checkTile(Entity entity) {
        int entity_L_X = entity.worldX + entity.solidArea.x;
        int entity_R_X = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entity_T_Y = entity.worldY + entity.solidArea.y;
        int entity_B_Y = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entity_L_Col = entity_L_X / gw.tileSize;
        int entity_R_Col = entity_R_X / gw.tileSize;
        int entity_T_Row = entity_T_Y / gw.tileSize;
        int entity_B_Row = entity_B_Y / gw.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entity_T_Row = (entity_T_Y - entity.speed) / gw.tileSize;
                tileNum1 = gw.tileM.mapTileNum[entity_L_Col][entity_T_Row];
                tileNum2 = gw.tileM.mapTileNum[entity_R_Col][entity_T_Row];
                if (gw.tileM.tile[tileNum1].collision == true || gw.tileM.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entity_B_Row = (entity_B_Y + entity.speed) / gw.tileSize;
                tileNum1 = gw.tileM.mapTileNum[entity_L_Col][entity_B_Row];
                tileNum2 = gw.tileM.mapTileNum[entity_R_Col][entity_B_Row];
                if (gw.tileM.tile[tileNum1].collision == true || gw.tileM.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entity_L_Col = (entity_L_X - entity.speed) / gw.tileSize;
                tileNum1 = gw.tileM.mapTileNum[entity_L_Col][entity_T_Row];
                tileNum2 = gw.tileM.mapTileNum[entity_L_Col][entity_B_Row];
                if (gw.tileM.tile[tileNum1].collision == true || gw.tileM.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entity_R_Col = (entity_R_X + entity.speed) / gw.tileSize;
                tileNum1 = gw.tileM.mapTileNum[entity_R_Col][entity_T_Row];
                tileNum2 = gw.tileM.mapTileNum[entity_R_Col][entity_B_Row];
                if (gw.tileM.tile[tileNum1].collision == true || gw.tileM.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            default:
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = -1;
        for (int i = 0; i < gw.obj.length; i++) {
            if (gw.obj[i] != null)
            {
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;

                gw.obj[i].solidArea.x = gw.obj[i].worldX + gw.obj[i].solidArea.x;
                gw.obj[i].solidArea.y = gw.obj[i].worldY + gw.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                    default:
                        break;
                }

                if(entity.solidArea.intersects(gw.obj[i].solidArea)) 
                {
                    if (gw.obj[i].collision == true) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gw.obj[i].solidArea.x = gw.obj[i].solidAreaDefaultX;
                gw.obj[i].solidArea.y = gw.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    // NPC Collision
    public int checkEntity(Entity entity, ArrayList<Entity> target) {
        int index = -1;


        for (int i = 0; i < target.size(); i++) {
            if (target.get(i) != null)
            {


                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;

                target.get(i).solidArea.x = target.get(i).worldX + target.get(i).solidArea.x;
                target.get(i).solidArea.y = target.get(i).worldY + target.get(i).solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                    default:
                        break;
                }

                if(entity.solidArea.intersects(target.get(i).solidArea)) {
                    if (target.get(i) != entity)
                    {
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target.get(i).solidArea.x = target.get(i).solidAreaDefaultX;
                target.get(i).solidArea.y = target.get(i).solidAreaDefaultY;
            }
        }

        return index;
    }

    public boolean checkPlayer(Entity entity)
    {
        boolean contactPlayer = false;

        entity.solidArea.x += entity.worldX;
        entity.solidArea.y += entity.worldY;

        gw.player.solidArea.x = gw.player.worldX + gw.player.solidArea.x;
        gw.player.solidArea.y = gw.player.worldY + gw.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
            default:
                break;
        }

        if(entity.solidArea.intersects(gw.player.solidArea))
        {
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gw.player.solidArea.x = gw.player.solidAreaDefaultX;
        gw.player.solidArea.y = gw.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
