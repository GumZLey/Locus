package entity;

import main.GameWindow;

public class Projectile extends Entity{
    Entity user;

    public Projectile(GameWindow gw){

        super(gw);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update() {
        if (user == gw.player)
        {
            int monsterIndex = gw.hitbox_checker.checkEntity(this, gw.monster);
            if (monsterIndex != -1)
            {
                gw.player.damageMonster(monsterIndex);
                alive = false;
            }
        }
        switch (direction) {
            case "up":
                worldY -= speed; break;
            case "down":
                worldY += speed; break;
            case "left":
                worldX -= speed; break;
            case "right":
                worldX += speed; break;
            default:
                break;
        }

        life--;
        if (life <= 0) {
            alive = false;
        }
    }
}
