package CompProject;

import java.awt.*;

public class TitleScreen {
    public static void draw(Graphics g){
        if(!SnakeWindow.gameActive) {
            g.setColor(Color.WHITE);
            SnakeWindow.drawCenteredString(g, "SNAKE", SnakeWindow.FRAMEWIDTH/2, SnakeWindow.FRAMEHEIGHT/6, 0, 0, SnakeWindow.f50);
//            SnakeWindow.drawCenteredString(g, "pussy", SnakeWindow.WIDTH / 2, 200, 0, 0, SnakeWindow.f30);
//            SnakeWindow.drawCenteredString(g, "ID: 300146320", SnakeWindow.WIDTH / 2, 235, 0, 0, SnakeWindow.f30);
            SnakeWindow.drawCenteredString(g, "PRESS 'SPACE' TO START", SnakeWindow.FRAMEWIDTH/2, (int)(0.9 * SnakeWindow.FRAMEHEIGHT), 0, 0, SnakeWindow.f50);
            SnakeWindow.drawDifficultyPicker(g, SnakeWindow.level);
        }
    }
}
