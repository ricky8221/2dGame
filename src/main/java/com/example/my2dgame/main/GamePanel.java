package com.example.my2dgame.main;

import com.example.my2dgame.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // Screen Setting
    final int originalTileSize = 16; //16 * 16 tiles
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48 * 48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // FPS
    int FPS = 60;
    final double drawInterval = 1000000000 / FPS; // 0.016666 seconds

    // FPS counter
    int currentFPS = 0;


    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this, keyH);

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    //#region Sleep method -- not prefer
//    @Override
//    public void run() {
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while (gameThread != null) {
//
//            // 1.Update information such as character position
//            update();
//
//            // 2. Draw the screen with the updated information
//            repaint();
//
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 100000;
//
//                if (remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long)remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    //#endregion

    //#region Delta method
    @Override
    public void run() {
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta --;
                drawCount++;
            }

            if (timer >= 1000000000) {
                currentFPS = drawCount; // Update FPS to be displayed
                drawCount = 0;
                timer = 0;
            }
        }
    }
    //#endregion

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);

        //#region Draw the FPS in the top right corner
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.drawString("FPS: " + currentFPS, screenWidth - 100, 30); // Position it in the top right corner
        //#endregion

        g2.dispose();
    }
}