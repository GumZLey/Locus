package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

import main.GameWindow;

public class TileManager {
    GameWindow gw;
    public Tile[] tile;
    List<String> tile_name;
    public String file_name;
    public int mapTileNum[][];
    public TileManager(GameWindow gw, String f_n)
    {
        this.gw = gw;
        tile = new Tile[500];
        this.file_name = f_n;
        mapTileNum = new int[gw.maxWorldCol][gw.maxWorldRow];
        tile_name = generateTileStrings();
        getTileImage();
        // loadMap("maps/tile_map.txt");
        loadMap("maps/" + file_name);
        tile[4].collision = true;
    }

    public List<String> generateTileStrings() {
        List<String> tileStrings = new ArrayList<>();

        for (int i = 1; i < 256; i++) {
            tileStrings.add(String.format("Tiles/tile%03d.png", i));
        }

        return tileStrings;
    }

    public void getTileImage() {
        tile[0] = new Tile();
        IntStream.range(1, tile_name.size() + 1).forEach(i -> {
            tile[i] = new Tile();
            try {
                tile[i].image = ImageIO.read(getClass().getResourceAsStream(tile_name.get(i - 1)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setCollision(){

    }

    public void loadMap(String filename) {
        try {
            InputStream is = getClass().getResourceAsStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            
            while (col < gw.maxWorldCol && row < gw.maxWorldRow)
            {
                String line = br.readLine();
                
                while (col < gw.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }
                if (col == gw.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {}
    }

    public void draw(Graphics2D g2d) 
    {
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gw.maxWorldCol && worldRow < gw.maxWorldRow)
        {
            int tileNum = mapTileNum[worldCol][worldRow];;

                    

            int worldX = worldCol * gw.tileSize;
            int worldY = worldRow * gw.tileSize;
            int screenX = worldX - gw.player.worldX + gw.player.screenX;
            int screenY = worldY - gw.player.worldY + gw.player.screenY;


            g2d.drawImage(tile[tileNum].image, screenX, screenY, gw.tileSize, gw.tileSize, null);

            worldCol++;

            if (worldCol == gw.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
