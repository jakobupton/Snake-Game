package CompProject;

import java.awt.*;

public class PauseScreen {
    public static void draw(Graphics g) {
        if(SnakeWindow.gamePaused) {
            g.setColor(Color.WHITE);
            SnakeWindow.drawCenteredString(g, "PAUSED", SnakeWindow.WIDTH / 2, SnakeWindow.HEIGHT / 10 + (3 * SnakeWindow.SCALE), 0, 0, SnakeWindow.f50);
            SnakeWindow.drawCenteredString(g, "PRESS 'SPACE' TO RESUME", SnakeWindow.WIDTH / 2, SnakeWindow.HEIGHT - SnakeWindow.HEIGHT / 6, 0, 0, SnakeWindow.f50);
            SnakeWindow.drawCenteredString(g, "PRESS 'G' TO ENABLE/DISABLE GRID", SnakeWindow.WIDTH / 2, SnakeWindow.HEIGHT - (SnakeWindow.HEIGHT / 10), 0, 0, SnakeWindow.f30);
            SnakeWindow.drawDifficultyPicker(g, SnakeWindow.level);
        }
    }
}
