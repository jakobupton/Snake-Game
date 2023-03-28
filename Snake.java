package CompProject;
import java.awt.*;

public class Snake {
    static int x;
    static int y;
    static int xv;
    static int yv;
    private static int v;
    static boolean isAlive = false;
    private static char currDir = 'R';
    private static char lastDir;
    private static char queueDir = ' ';


    public Snake(){
        x = SnakeWindow.WIDTH/2 - ((SnakeWindow.WIDTH/2)%SnakeWindow.SCALE);
        y = SnakeWindow.HEIGHT/2- ((SnakeWindow.HEIGHT/2)%SnakeWindow.SCALE);
        v = SnakeWindow.SCALE;
        xv = v;
    }

    public static void leftKeyPressed() {
        char dir = 'L';
        if (lastDir != 'R' && lastDir != 'L'){
            setDir(dir);
        }else{
            queueDir = 'L';
        }
    }

    public static void rightKeyPressed() {
        char dir = 'R';
        if (lastDir != 'L' && lastDir != 'R'){
            setDir(dir);
        }else{
            queueDir = 'R';
        }
    }

    public static void downKeyPressed() {
        char dir = 'D';
        if (lastDir != 'U' && lastDir != 'D'){
            setDir(dir);
        }else{
            queueDir = 'D';
        }
    }

    public static void upKeyPressed() {
        char dir = 'U';
        if (lastDir != 'D' && lastDir != 'U'){
            setDir(dir);
        }else{
            queueDir = 'U';
        }

    }

    public static void restInPeace(){
        isAlive = false;
        x = x - xv;
        y = y - yv;
    }

    public static void resurrect(){
        isAlive = true;
        x = SnakeWindow.WIDTH/2 - ((SnakeWindow.WIDTH/2)%SnakeWindow.SCALE);
        y = SnakeWindow.HEIGHT/2- ((SnakeWindow.HEIGHT/2)%SnakeWindow.SCALE);
        Tail.tail.clear();
        lastDir = ' ';
        queueDir = ' ';
        currDir = 'R';
        setDir(currDir);
        SnakeWindow.timeStart = System.nanoTime();
    }
    public static void setDir(char dir){
            switch (dir) {
                case 'L':
                    if (lastDir == 'R'){break;}
                    currDir = dir;
                    xv = -1 * v;
                    yv = 0;
                    break;
                case 'R':
                    if (lastDir == 'L'){break;}
                    currDir = dir;
                    xv = v;
                    yv = 0;
                    break;
                case 'D':
                    if (lastDir == 'U'){break;}
                    currDir = dir;
                    xv = 0;
                    yv = v;
                    break;
                case 'U':
                    if (lastDir == 'D'){break;}
                    currDir = dir;
                    xv = 0;
                    yv = -1 * v;
                    break;
            }
    }
    public static void drawSnakePiece(Graphics g, int x, int y, Color snakeColor){
        g.setColor(snakeColor);
        g.fillRect(x, y, SnakeWindow.SCALE, SnakeWindow.SCALE);
    }

    public static void draw(Graphics g){
        Color colorPiece;
        if(SnakeWindow.gameActive) {
            if(isAlive) {
                colorPiece = new Color(0,127,255);
            }else{
                colorPiece = new Color(255, 30, 0);
            }
            drawSnakePiece(g,x,y,colorPiece);
        }
    }

    public static void updateFrame() {
        if (isAlive) {
            x += xv;
            y += yv;
            lastDir = currDir;
            if(queueDir != ' '){
                setDir(queueDir);
                queueDir = ' ';
            }
            if (((x + SnakeWindow.SCALE) > SnakeWindow.WIDTH) || (x < SnakeWindow.OFFSET) || ((y + SnakeWindow.SCALE) > SnakeWindow.HEIGHT || y < SnakeWindow.OFFSET)){ //kill snake if touching the edge
                restInPeace();
            }
        }
    }
}