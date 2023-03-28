package CompProject;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.concurrent.ThreadLocalRandom;


public class Food implements ImageObserver {
    public static int score = 0;
    private static int x;
    private static int y;
    public static int minutes, seconds, milliseconds;
    public static String output;

    public static void draw(Graphics g){
        if (SnakeWindow.gameActive) {
            if (Snake.isAlive) {
                g.setColor(new Color(255, 0, 0));
                g.fillOval(x + SnakeWindow.SCALE / 4, y + SnakeWindow.SCALE / 4, SnakeWindow.SCALE - SnakeWindow.SCALE / 3, SnakeWindow.SCALE - SnakeWindow.SCALE / 3);
                g.setColor(new Color(0, 255, 0));
                g.fillOval(x + SnakeWindow.SCALE / 3, y + SnakeWindow.SCALE / 8, SnakeWindow.SCALE / 5, SnakeWindow.SCALE / 9);
                g.setColor(Color.WHITE);
                SnakeWindow.drawCenteredString(g, String.valueOf(score), SnakeWindow.OFFSET + SnakeWindow.SCALE/2 , SnakeWindow.OFFSET/2, 0, 0, SnakeWindow.f30);
                SnakeWindow.drawCenteredString(g, output, SnakeWindow.WIDTH - (2 * SnakeWindow.OFFSET), SnakeWindow.OFFSET/2, 0, 0, SnakeWindow.f30);
            }
        }
    }
    public static void newFoodPos(){ //Chooses a random spot on the grid for food to spawn,
        ThreadLocalRandom r = ThreadLocalRandom.current();
        int randCols = r.nextInt(SnakeWindow.OFFSET/SnakeWindow.SCALE, (SnakeWindow.WIDTH - SnakeWindow.OFFSET)/SnakeWindow.SCALE);
        int randRow = r.nextInt(SnakeWindow.OFFSET/SnakeWindow.SCALE, (SnakeWindow.HEIGHT - SnakeWindow.OFFSET)/SnakeWindow.SCALE);
            x = (randCols * SnakeWindow.SCALE);
            y = (randRow * SnakeWindow.SCALE);
            if (Tail.tail != null) {
                if (Tail.tail.size() > 1) {
                    for (Tail tail : Tail.tail) {
                        if (x == tail.x && y == tail.y) {
                            newFoodPos();
                        }
                    }
                }
            }
    }


    public static void updateFrame(){
        if (Snake.x == x && Snake.y == y ){//player x and food x can line up because the grid form screen
            newFoodPos();
            score++;
        }
        minutes = (SnakeWindow.timeElapsed/60000)%60;
        seconds = (SnakeWindow.timeElapsed/1000) % 60;
        milliseconds = (SnakeWindow.timeElapsed % 1000);
        output = String.format("%02d:%02d:%03d", minutes, seconds,milliseconds);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}