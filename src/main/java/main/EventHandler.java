package main;

import java.awt.Rectangle;

public class EventHandler {
    GameWindow gw;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GameWindow gw) {
        this.gw = gw;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        if (hit(30, 17, "right") == true)
        {
            // damage(gw.playState);
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gw.player.solidArea.x = gw.player.worldX + gw.player.solidArea.x;
        gw.player.solidArea.y = gw.player.worldY + gw.player.solidArea.y;
        eventRect.x = eventCol * gw.tileSize + eventRect.x;
        eventRect.y = eventRow * gw.tileSize + eventRect.y;

        if (gw.player.solidArea.intersects(eventRect)) {
            if (gw.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any"))
            {
                hit = true;
            }

            
        }
        gw.player.solidArea.x = gw.player.solidAreaDefaultX;
        gw.player.solidArea.y = gw.player.solidAreaDefaultY;

        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        return hit;
    }
    public void damage(int gameState)
    {
        System.out.println("HITTT");
        gw.gameState = gameState;
        gw.player.life--;
    }

}
