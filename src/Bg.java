/*
 * @Description: 
 * @Logo:                                                               ▄   ▄
 * ░██████╗██╗░░██╗░█████╗░░██╗░░░░░░░██╗███╗░░██╗  ░█████╗░░█████╗░██╗ █▀█▀█
 * ██╔════╝██║░░██║██╔══██╗░██║░░██╗░░██║████╗░██║  ██╔══██╗██╔══██╗██║ █▄█▄█
 * ╚█████╗░███████║███████║░╚██╗████╗██╔╝██╔██╗██║  ██║░░╚═╝███████║██║ ███  ▄▄
 * ░╚═══██╗██╔══██║██╔══██║░░████╔═████║░██║╚████║  ██║░░██╗██╔══██║██║ ████▐█ █
 * ██████╔╝██║░░██║██║░░██║░░╚██╔╝░╚██╔╝░██║░╚███║  ╚█████╔╝██║░░██║██║ ████   █
 * ╚═════╝░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░╚═╝░░╚══╝  ░╚════╝░╚═╝░░╚═╝╚═╝ ▀▀▀▀▀▀▀
 * @Author: Shawn C
 * Copyright (c) 2022 by Shawn C., All Rights Reserved. 
 */
import java.awt.*;
public class Bg {
    static int level = 1;
    int goal = level * 15;
    static int count = 0;
    static int diamondNum = 3;
    static boolean diamondFlag = false;
    long startTime;
    long endTime;
    int price = (int) (Math.random() * 10);
    boolean shop = false;
    Image bg = Toolkit.getDefaultToolkit().getImage("img/bg.jpg");
    Image bg1 = Toolkit.getDefaultToolkit().getImage("img/bg1.jpg");
    Image peo = Toolkit.getDefaultToolkit().getImage("img/peo.png");
    Image diamond = Toolkit.getDefaultToolkit().getImage("img/diamond.png");
    Image logo = Toolkit.getDefaultToolkit().getImage("img/logo.png");
    void paintSelf(Graphics g) {
        g.drawImage(bg1, 0, 0, null);
        g.drawImage(bg, 0, 200, null);
        switch (GameWin.state) {
            case 0:
                g.drawImage(logo, 300, 220, null);
                drawWord(g, 70, Color.yellow, "Gold Miner", 180, 450);
                drawWord(g, 50, Color.yellow, "Right Click to Start", 145, 550);
                break;
            case 1:
                g.drawImage(peo, 310, 50, null);
                drawWord(g, 30, Color.yellow, "Gold: " + count, 30, 150);
                g.drawImage(diamond, 450, 40, null);
                drawWord(g, 30, Color.cyan, "x" + diamondNum, 510, 90);
                drawWord(g, 20, Color.yellow, "Level. " + level, 30, 60);
                drawWord(g, 30, Color.yellow, "Target: " + goal, 30, 110);
                endTime = System.currentTimeMillis();
                long tim = 20 - (endTime - startTime) / 1000;
                drawWord(g, 30, Color.white, "Time: " + (tim > 0 ? tim : 0), 520, 150);
                break;
            case 2:
                g.drawImage(diamond, 350, 380, null);
                drawWord(g, 30, Color.YELLOW, "Price: " + price, 330, 500);
                drawWord(g, 30, Color.YELLOW, "Purchase?", 310, 550);
                if (shop) {
                    count = count - price;
                    diamondNum++;
                    shop = false;
                    GameWin.state = 1;
                    startTime = System.currentTimeMillis();
                }
                break;
            case 3:
                drawWord(g, 80, Color.YELLOW, "FAIL", 280, 350);
                drawWord(g, 80, Color.YELLOW, "Gold: " + count, 200, 450);
                break;
            case 4:
                drawWord(g, 80, Color.YELLOW, "SUCCESS", 250, 350);
                drawWord(g, 80, Color.YELLOW, "Gold: " + count, 200, 450);
                break;
            default:
        }
    }
    boolean gameTime() {
        long tim = (endTime - startTime) / 1000;
        if (tim > 20) {
            return true;
        }
        return false;
    }
    void reGame() {
        level = 1;
        goal = level * 15;
        count = 0;
        diamondNum = 3;
        diamondFlag = false;
    }
    public static void drawWord(Graphics g, int size, Color color, String str, int x, int y) {
        g.setColor(color);
        g.setFont(new Font("Font", Font.BOLD, size));
        g.drawString(str, x, y);
    }
}
