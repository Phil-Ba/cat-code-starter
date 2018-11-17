package catcode;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 */
public class MainLevel1 {
    
    public static void main(String[] args) {
//        System.out.println(IOUtils.readFileAsList("test.txt"));
//        IOUtils.write("out.txt", "1","2","3");
//        solve1(IOUtils.readFile("level1.txt"));
        Scanner sc = new Scanner(System.in);
        int middle = 225;
        int bottom = 450;
        int botMid = 150 - 75;
        int topMid = 450 - 75;
        Double ballPredY = null;
        System.out.println("Printing the file passed in:");
        while (true) {
            String[] s = sc.nextLine().split(" ");
            int playerX = Integer.parseInt(s[1]);
            int playerMovePrev = Integer.parseInt(s[2]);
            playerX += playerMovePrev;
            
            s = sc.nextLine().split(" ");
            s = sc.nextLine().split(" ");
            double ballX = Double.parseDouble(s[1]);
            double ballY = Double.parseDouble(s[2]);
            double velX = Double.parseDouble(s[3]);
            double velY = Double.parseDouble(s[4]);
            s = sc.nextLine().split(" ");
            
            int move = 0;
            
            int curTop = playerX;
            int curBot = playerX + 150;
            
            if (velX > 0) {
                ballPredY = null;
                move = moveToPosition(middle, curTop);
            } else {
                
                double ticks = Math.abs((ballX+15) / velX);
                double ballYNext = velY * ticks + ballY;
                boolean outtop = ticks * velY + ballY < 0;
                boolean outbot = ticks * velY + ballY > 600;
                
//                if (ballPredY != null) {
//                    move = moveToPosition((int) (ballPredY - 75), curTop);
//                    ballPredY = null;
//                } else
                    if (outbot) {
                    double ticksTilBorder = Math.abs((600 - ballY - 15) / velY);
                    double xBorderPos = ballX + velX * ticksTilBorder;
                    double ticksTilEnd = Math.abs((xBorderPos - 15) / velX);
                    ballPredY = ticksTilEnd * velY * -1 + 600;
                    move = moveToPosition((int) (ballPredY - 60), curTop);
                } else if (outtop) {
                    double ticksTilBorder = Math.abs((ballY - 15) / velY);
                    double xBorderPos = ballX + velX * ticksTilBorder;
                    double ticksTilEnd = Math.abs((xBorderPos - 15) / velX);
                    ballPredY = ticksTilEnd * velY * -1 + 0;
                    move = moveToPosition((int) (ballPredY - 60), curTop);
                } else {
                    move = moveToPosition((int) ballYNext - 65, curTop);
                }
            }
            
            
            System.out.println("move " + move);
        }
    }
    
    private static int moveToPosition(int moveto, int curTop) {
        int move;
        if (moveto == curTop) {
            move = 0;
        } else if (curTop < moveto) {
            move = Math.min(36, moveto - curTop);
        } else {
            move = Math.max(-36, moveto - curTop);
        }
        return move;
    }
    
    static void solve1(Stream<String> input) {
    
    }
    
}
