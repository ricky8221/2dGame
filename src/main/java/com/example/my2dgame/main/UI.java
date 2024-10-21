package com.example.my2dgame.main;

import com.example.my2dgame.object.ObjKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial40, arial80Bold;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public double playTime;
    DecimalFormat df = new DecimalFormat("0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial80Bold = new Font("Arial", Font.BOLD, 80);
        ObjKey objKey = new ObjKey(gp);
        keyImage = objKey.image;
    }

    public void showMessage(String message) {
        this.message = message;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if (gameFinished) {
            g2.setFont(arial40);
            g2.setColor(Color.WHITE);
            String text;
            int textWidth;
            int x;
            int y;

            text = "You found the treasure";
            textWidth = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x  = gp.screenWidth / 2 - textWidth / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            text = "Your time is: " + df.format(playTime) + "!";
            textWidth = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x  = gp.screenWidth / 2 - textWidth / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 4);
            g2.drawString(text, x, y);

            g2.setFont(arial80Bold);
            g2.setColor(Color.YELLOW);
            text = "Congratulations!";
            textWidth = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x  = gp.screenWidth / 2 - textWidth / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 3);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        } else {
            g2.setFont(arial40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);

            // Time
            playTime += (double) 1/60;
            g2.drawString("Time: " + df.format(playTime), gp.tileSize * 11, 65);

            // Message
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                    message = "";
                }
            }
        }
    }
}
