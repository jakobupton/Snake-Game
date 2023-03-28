package CompProject;
import java.awt.*;
import java.awt.event.*;

public final class SnakeWindow extends Frame implements KeyListener{

    public static int SCALE = 40;
    public static int WIDTH = 0, HEIGHT = 0;
    public static int FRAMEWIDTH = 0, FRAMEHEIGHT = 0;

    public static int OFFSET = SCALE;

    public static int tickNum = 0;
    boolean running = true;

    public static Font f50 = new Font("Minecraft", Font.PLAIN, 50);
    public static Font f30 = new Font("Minecraft", Font.PLAIN, 30);

    //vars for information about the game state
    public static boolean gameActive = false;
    public static boolean gamePaused = false;
    private static int difficultyMultiplier = 4;
    public static int level = 2;
    private static boolean gridEnabled = true;
    public static double timeStart = 0;
    public static int timeElapsed = 0;

    private SnakeWindow() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = (int)(0.90*screen.width - (0.90 * screen.width)%SCALE) + SCALE;
        HEIGHT = (int)(0.90 * screen.height - (0.90 * screen.height)% SCALE) - 2*OFFSET;
        setSize(WIDTH + OFFSET, HEIGHT + OFFSET);
        FRAMEWIDTH = getSize().width;
        FRAMEHEIGHT = getSize().height;
        setUndecorated(true);
        setVisible(true);
        setBackground(Color.BLACK);
        addKeyListener(this);
        setLocationRelativeTo(null);
    }
    public static void drawCenteredString(Graphics g, String text, int xx, int yy, int ww, int hh, Font ff) {
        FontMetrics metrics = g.getFontMetrics(ff);
        int x = xx + (ww - metrics.stringWidth(text)) / 2;
        int y = yy + ((hh - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(ff);
        g.drawString(text, x, y);
    }

    public static void tick(SnakeWindow win){
        tickNum++;
        if (Snake.isAlive) {
            if (tickNum == difficultyMultiplier) {
                win.updateFrame();
                tickNum = 0;
            }
            if (gameActive) {
                timeElapsed = (int) ((System.nanoTime() - timeStart) / 1000000);
            }
        }else{
            win.updateFrame();
            tickNum = 0;
        }
        win.repaint();
    }
    public void paint(Graphics g){
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
    public static void paintComponent(Graphics g){
        Tail.draw(g);
        Snake.draw(g);
        drawGrid(g);
        Food.draw(g);
        DeathScreen.draw(g);
        TitleScreen.draw(g);
        PauseScreen.draw(g);
    }

    public static void drawDashedRect(Graphics g, int x1, int y1, int width, int height){

        // Create a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the stroke of the copy, not the original
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{9}, 1);
        g2d.setStroke(dashed);

        // Draw to the copy

        g2d.drawLine(x1, y1, x1+width, y1); //TOP HORIZONTAL LINE
        g2d.drawLine(x1, y1,  x1, y1+height); // LEFT VERTICAL LINE
        g2d.drawLine(x1+width, y1, x1+width, y1+height); //RIGHT VERTICAL LINE
        g2d.drawLine(x1, y1+height, x1+width, y1+height); //BOTTOM VERTICAL LINE

        // Get rid of the copy
        g2d.dispose();
    }

    private static void drawGrid(Graphics g){
        if(gridEnabled) {
            g.setColor(new Color(60, 60, 60));
            for (int i = 1; i <  (HEIGHT - OFFSET)/SCALE; i++) { //horizontal lines
                g.drawLine(OFFSET, (i*SCALE)+OFFSET, WIDTH, (i*SCALE)+OFFSET);
            }
            for (int i = 1; i < (WIDTH - OFFSET)/SCALE; i++) { //vertical lines
                g.drawLine((i*SCALE)+OFFSET, OFFSET, (i*SCALE)+OFFSET, HEIGHT);
            }
        }
        g.setColor(Color.BLUE);
        g.drawRect(3, 3, FRAMEWIDTH-4, FRAMEHEIGHT-4);
        g.setColor(Color.YELLOW);
        //g.drawRect(OFFSET,  OFFSET, WIDTH - OFFSET, HEIGHT - OFFSET);
        drawDashedRect(g, OFFSET, OFFSET, WIDTH - OFFSET, HEIGHT - OFFSET);
    }

    private void updateFrame() {
        if (gameActive && !gamePaused) {
            Snake.updateFrame();
            Tail.updateFrame();
        }
        Food.updateFrame();
    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case 37: //key leftArrow
            case 65: //key a
                Snake.leftKeyPressed();
                break;
            case 39: //key rightArrow
            case 68: //key d
                Snake.rightKeyPressed();
                break;
            case 40: //key downArrow
            case 83: //key s
                Snake.downKeyPressed();
                break;
            case 38: //key upArrow
            case 87: //key w
                Snake.upKeyPressed();
                break;
            case 32: //key space
                spacePressed();
                break;
            case 71: //key g
                gridEnabled = !gridEnabled;
                break;
            case 49: //key 1
                setDifficulty(1);
                break;
            case 50: //key 2
                setDifficulty(2);
                break;
            case 51: //key 3
                setDifficulty(3);
                break;
            case 27:
                System.exit(0);
        }
    }

    private void setDifficulty(int difficulty){
        if(!Snake.isAlive) {
            switch (difficulty) {
                case 1:
                    difficultyMultiplier = 6;
                    break;
                case 2:
                    difficultyMultiplier = 5;
                    break;
                case 3:
                    difficultyMultiplier = 3;
                    break;
                default:

            }
            level = difficulty;
        }
    }

    private void spacePressed(){
        if(!Snake.isAlive) {
            gameActive = true;
            Snake.resurrect();
            Food.score = 0;
            Food.newFoodPos();
        }else{
            gamePaused = !gamePaused;
        }
    }


    public void	keyReleased(KeyEvent e) {
    }

    public void	keyTyped(KeyEvent e) {

    }

    public static void drawDifficultyPicker(Graphics g, int level){
        int gridX = (FRAMEWIDTH/SCALE);
        int gridY = (FRAMEHEIGHT/SCALE);
        switch(level){
            case 1:
                drawDashedRect(g, (int) ((0.4*FRAMEWIDTH)- SCALE), (int)(0.5 * FRAMEHEIGHT) - SCALE, 2* SCALE, 2 * SCALE );
                break;
            case 2:
                drawDashedRect(g, (int) ((0.5*FRAMEWIDTH) - SCALE), (int)(0.5 * FRAMEHEIGHT) - SCALE, 2* SCALE, 2 * SCALE );
                break;
            case 3:
                drawDashedRect(g, (int) ((0.6*FRAMEWIDTH) - SCALE),  (int)(0.5 * FRAMEHEIGHT) - SCALE, 2* SCALE, 2 * SCALE );
                break;

        }
        drawCenteredString(g, "1", (int) (0.4*FRAMEWIDTH),(int)(0.5 * FRAMEHEIGHT) , 0, 0, f50);
        drawCenteredString(g, "EASY", (int) (0.4*FRAMEWIDTH),(int)(0.5 *FRAMEHEIGHT) - 2*SCALE , 0, 0, f30);
        drawCenteredString(g, "2", (int) (0.5*FRAMEWIDTH), (int)(0.5 * FRAMEHEIGHT), 0, 0, f50);
        drawCenteredString(g, "NORMAL", (int) (0.5*FRAMEWIDTH), (int)(0.5 * FRAMEHEIGHT) - 2*SCALE, 0, 0, f30);
        drawCenteredString(g, "3", (int) (0.6*FRAMEWIDTH), (int)(0.5 * FRAMEHEIGHT), 0, 0, f50);
        drawCenteredString(g, "HARD", (int) (0.6*FRAMEWIDTH), (int)(0.5 * FRAMEHEIGHT) - 2*SCALE, 0, 0, f30);
    }
    public static void main(String[] args) {
        SnakeWindow win = new SnakeWindow();
        Snake playerSnake = new Snake();
        long lastTime = System.nanoTime();
        final double ticks = 60D;
        double ns = 1000000000 / ticks;
        double deltaTime = 0;

        while(win.running){
            long now = System.nanoTime();
            deltaTime += (now - lastTime) / ns;
            lastTime = now;
            if(deltaTime >= 1){
                tick(win);
                deltaTime--;
            }
        }
    }
}
