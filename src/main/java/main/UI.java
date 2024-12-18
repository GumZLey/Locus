package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Entity;
import entity.Player;
import object.Object_Heart;
import object.Object_head;

public class UI {
    GameWindow gw;
    Player player;
    Graphics2D g2d;
    Font arial_40;
    BufferedImage heart_full, heart_gone;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public int score = 0;

    public UI(GameWindow gw, Player player)
    {
        this.player = player;
        this.gw = gw;
        arial_40 = new Font("Arial", Font.PLAIN, 40);

        Object_head heart = new Object_Heart(gw);
        heart_full = heart.image;
        heart_gone = heart.image2;
    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2d)
    {
        this.g2d = g2d;

        g2d.setFont(arial_40);
        g2d.setColor(Color.white);

        if (gw.gameState == gw.playState)
        {
            drawPlayerLife();
            drawScoreScreen();
        }
        if (gw.gameState == gw.pauseState)
        {
            drawPlayerLife();
            
            if (player.life <= 0)
            {
                drawDiedScreen();
            }else {
                drawPauseScreen();
            }
        }
    }

    public void drawPauseScreen() {
        String text = "PAUSE";
        int x = getXforCenteredText(text);
        int y = gw.screenHeight / 2;

        g2d.drawString(text, x, y);
    }

    public void drawDiedScreen() {
        String text = "You Died";
        int x = getXforCenteredText(text);
        int y = gw.screenHeight / 2;

        g2d.drawString(text, x, y);
    }

    public void drawScoreScreen() {
        String text = "Score: " + score;
        int x = gw.screenWidth - 300;
        int y = 50;

        g2d.drawString(text, x, y);
    }

    public void drawPlayerLife()
    {

        int x = gw.tileSize / 2;
        int y = gw.tileSize / 2;
        int i = 0;
        while (i < gw.player.maxLife)
        {
            g2d.drawImage(heart_gone, x, y, null);
            i++;
            x += gw.tileSize;
        }

        // Reset
        x = gw.tileSize/2;
        y = gw.tileSize/2;
        i = 0;

        while (i < gw.player.life) {
            g2d.drawImage(heart_full, x, y, null);
            i++;
            x+= gw.tileSize;
        }
    }

    public int getXforCenteredText(String text)
    {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = gw.screenWidth / 2 - length / 2;
        return x;
    }
}
