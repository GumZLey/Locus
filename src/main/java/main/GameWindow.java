package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import entity.Entity;
import entity.Player;
import object.Object_head;
import sound.Sound;
import tile.TileManager;

public class GameWindow extends JPanel implements Runnable{
    
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    TileManager tileM = new TileManager(this, "layer1_Tile_Layer1.txt");
    TileManager tileM_layer2 = new TileManager(this, "layer1_Tile_Layer2.txt");
    Thread  gameThread;
    
    public AssetSetter aSetter = new AssetSetter(this);
    public Hitbox hitbox_checker = new Hitbox(this);
    public Player player = new Player(this, keyH);
    public Object_head obj[] = new Object_head[10];
    public Entity npc[] = new Entity[10];
    public ArrayList<Entity> monster = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public UI ui = new UI(this, player);
    public EventHandler eHandler = new EventHandler(this);

    public int gameState;
    public final int tileState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    int FPS = 60;

    
    public final int tileSize = 32;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = Image_File.FRAMEWIDTH;
    public final int screenHeight= Image_File.FRAMEHEIGHT;
    
    public final int maxWorldCol = 85;
    public final int maxWorldRow = 48;
    
    int monster_mul = 1;
    

    public GameWindow() {
        this.setPreferredSize(new Dimension(Image_File.FRAMEWIDTH, Image_File.FRAMEHEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        AddComponents();
    }

    public void setupGame() {
        aSetter.setNPC();
        aSetter.setMonster(12);
        playMusic(0);
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void AddComponents() {

    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }

            if (timer >= 1_000_000_000) {
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState)
        {
            player.update();

            if (monster.size() <= 0) {monster_mul++; aSetter.setMonster(12 * monster_mul);}

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null)
                {
                    npc[i].update();
                }
            }

            for (int i = 0; i < monster.size(); i++) {

                if (monster.get(i) != null)
                {
                    monster.get(i).update();
                }
            }

            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null)
                {
                    if (projectileList.get(i).alive == true)
                    {
                        projectileList.get(i).update();
                    }
                    if (projectileList.get(i).alive == false){
                        projectileList.remove(i);
                    }
                    
                }
            }
        }
        if (gameState == pauseState)
        {
            
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // g2d.drawImage(Pic, 0, 0, null);

        if (gameState == tileState)
        {
            
        }
        else
        {
            tileM.draw(g2d);
            tileM_layer2.draw(g2d);

            for (int i = 0; i < obj.length; i++)
            {
                if (obj[i] != null){
                    obj[i].draw(g2d, this);
                }
            }
            player.draw(g2d);

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null){
                    npc[i].draw(g2d);
                }
            }

            for (int i = 0; i < monster.size(); i++) {
                if (monster.get(i) != null){
                    monster.get(i).draw(g2d);
                }
            }

            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null){
                    projectileList.get(i).draw(g2d);
                }
            }

            ui.draw(g2d);
        }
        

        g2d.dispose();
    }

    public void playMusic(int i) 
    {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic()
    {
        music.stop();
    }

    public void playSE(int i)
    {
        se.setFile(i);
        se.play();
    }
}
