package entity;

import main.GamePanel;
import main.KeyHandler;
import main.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int standCounter;
    boolean moving = false;
    int pixelCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(1, 1, 46, 46);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        up1 = setUp("/player/boy_up_1");
        up2 = setUp("/player/boy_up_2");
        down1 = setUp("/player/boy_down_1");
        down2 = setUp("/player/boy_down_2");
        left1 = setUp("/player/boy_left_1");
        left2 = setUp("/player/boy_left_2");
        right1 = setUp("/player/boy_right_1");
        right2 = setUp("/player/boy_right_2");
    }

    public void update() {

        if (!moving) {
            if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
                if (keyH.upPressed) {
                    direction = "up";
                } else if (keyH.downPressed) {
                    direction = "down";
                } else if (keyH.leftPressed) {
                    direction = "left";
                } else if (keyH.rightPressed) {
                    direction = "right";
                }

                moving = true;

                // Check tile collision
                collisionOn = false;
                gp.cChecker.checkTile(this);
                int objIdx = gp.cChecker.checkObject(this, true);
                pickupObject(objIdx);

                // Check the NPC collision
                int npcIdx = gp.cChecker.checkEntity(this, gp.npc);
                interactNpc(npcIdx);
            } else {
                standCounter++;
                if (standCounter == 20) {
                    spriteNum = 1;
                    standCounter = 0;
                }
            }
        }

        if (moving) {
            // If collision is false, player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            spriteCounter++;
            if (spriteCounter >= 12) {
                if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 1;

                spriteCounter = 0;
            }

            pixelCounter += speed;
            if (pixelCounter == 48) {
                moving = false;
                pixelCounter = 0;
            }
        }

    }

    public void pickupObject(int objIdx) {
        if (objIdx != 999) {

        }
    }

    public void interactNpc(int i) {
        System.out.println("You are hitting NPC");
    }

    public void draw(Graphics g2) {
        BufferedImage image = null;

            switch (direction) {
                case "up":

                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
        };

        g2.drawImage(image, screenX, screenY,null);

        //#region Dev purpose, drawing the collision vox
        g2.setColor(Color.RED);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        //#endregion
    }
}
