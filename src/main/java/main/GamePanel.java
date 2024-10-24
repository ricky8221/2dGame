package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // Screen Setting
    final int originalTileSize = 16; //16 * 16 tiles
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48 * 48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // World setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;
    final double drawInterval = 1000000000 / FPS; // 0.016666 seconds

    // FPS counter
    int currentFPS = 0;

    KeyHandler keyH = new KeyHandler(this);
    TileManager tileM = new TileManager(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssertSetter aSetter = new AssertSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // Entity of object
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10];
    public Entity[] npc = new Entity[10];

    // Game state
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;


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

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNpc();
        playMusic(0);
        stopMusic();
        gameState = playState;
    }


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
        if (gameState == playState) {
            // Player
            player.update();

            // NPC
            for (int i = 0; i < obj.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Tile
        tileM.draw(g2);

        // Objects
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // NPC
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].draw(g2);
            }
        }

        //Debug
        long drawStart = 0;
        drawStart = System.nanoTime();

        // Player
        player.draw(g2);

        //UI
        ui.draw(g2);


        //#region Draw the FPS in the top right corner
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.drawString("FPS: " + currentFPS, screenWidth - 100, 30); // Position it in the top right corner
        long drawEnd = 0;
        if (keyH.checkDrawTime) {
            drawEnd = System.nanoTime();
            long drawTime = (drawEnd - drawStart);
            g2.drawString("Draw time: " + drawTime, 10, 400);
        }
        //#endregion

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
