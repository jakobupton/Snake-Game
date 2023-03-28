package CompProject;

import java.awt.*;

public class DeathScreen {
    public static void draw(Graphics g){
        if(SnakeWindow.gameActive){
            if(!Snake.isAlive) {
                g.setColor(Color.WHITE);
                SnakeWindow.drawCenteredString(g, "You died!", SnakeWindow.WIDTH / 2, SnakeWindow.HEIGHT / 10, 0, 0, SnakeWindow.f50);
                SnakeWindow.drawCenteredString(g, "You ate:", SnakeWindow.WIDTH / 2, SnakeWindow.HEIGHT / 6, 0, 0, SnakeWindow.f30);
                SnakeWindow.drawCenteredString(g, Food.score + " apples", SnakeWindow.WIDTH / 2, SnakeWindow.HEIGHT / 6 + (int) (SnakeWindow.f30.getSize2D()), 0, 0, SnakeWindow.f30);
                SnakeWindow.drawCenteredString(g, "Played for: " + Food.output, SnakeWindow.WIDTH / 2, SnakeWindow.HEIGHT / (int) (SnakeWindow.f30.getSize2D()) + (int) (SnakeWindow.f30.getSize2D()), 0, 0, SnakeWindow.f30);
                SnakeWindow.drawCenteredString(g, "PRESS 'SPACE' TO RESTART", SnakeWindow.WIDTH / 2, SnakeWindow.HEIGHT - SnakeWindow.HEIGHT / 6, 0, 0, SnakeWindow.f50);
                SnakeWindow.drawDifficultyPicker(g, SnakeWindow.level);
            }
        }
    }
}
