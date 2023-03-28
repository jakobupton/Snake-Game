package CompProject;
import java.awt.*;
import java.util.*;

public class Tail {
    int x;
    int y;
    public static ArrayList<Tail> tail = new ArrayList<>();

    public Tail(int xx, int yy){
        x = xx;
        y = yy;

    }

    public static double lerp(double from, double to, double amt){ return (from + amt * (to - from)); }

    public static void addPiece(Tail newPiece){ tail.add(newPiece); }
    public static void updateFrame(){
        if(Snake.isAlive) {
            for (int i = tail.size()-1; i >= 0; i--) {
                if (i < 1) {
                    tail.get(i).x = Snake.x;
                    tail.get(i).y = Snake.y;
                } else {
                    tail.get(i).x = tail.get(i - 1).x;
                    tail.get(i).y = tail.get(i - 1).y;
                }
            }
            if(tail.size() <= Food.score) { addPiece(new Tail(Snake.x, Snake.y)); }
            //Food.setTail(tail);
            for (int i = 2; i < tail.size() - 1; i++) { //check every instance of touching with tail besides the one right behind the head because it'll kill it when it eats food;
                if (Math.sqrt((Math.pow(tail.get(i).x - Snake.x, 2) + Math.pow(tail.get(i).y - Snake.y, 2))) < 1) { //distance formula between snake.xy and tail(i).xy
                    Snake.restInPeace();
                }
            }
        }
    }

    public static void draw(Graphics gr){

        int r, g, b;
        Color pieceColor;
        for(int i = 1; i <= tail.size()-1; i++) {
            if(Snake.isAlive) {
                g = (int)lerp(127, 255,(double) i/tail.size());
                b = (int)lerp(127, 255,1-((double) i/tail.size()));
                pieceColor = new Color(0, g, b);
            }else{
                g = (int)lerp(30, 150,(double) i/tail.size());
                r = (int)lerp(200, 255,1-((double) i/tail.size()));
                pieceColor = new Color(r, g, 0);
            }
            Snake.drawSnakePiece(gr, tail.get(i).x, tail.get(i).y, pieceColor);
        }
    }
}
