package catcode.level3;

import java.util.ArrayList;
import java.util.List;

public class Invader {

    final int xOri;
    final int yOri;

    public static enum Direction {
        North, East, South, West;
    }

    double x;
    double y;
    int maxX;
    int maxY;
    int spawnTime;
    double speed;
    int id;
    Direction direction = Direction.East;
    List<String> postitions = new ArrayList<>();

    public Invader(int x, int y, int maxX, int maxY, double speed, int spawnTime, int id) {
        this.x = x;
        this.y = y;
        this.xOri = x;
        this.yOri = y;
        this.maxX = maxX;
        this.maxY = maxY;
        this.spawnTime = spawnTime;
        this.speed = speed;
        this.id = id;
        postitions.add(x + " " + y);
    }

    void reset() {
        x = xOri;
        y = yOri;
        postitions.clear();
        postitions.add(x + " " + y);
    }

    void move(Direction direction) {
        switch (direction) {
            case North:
                y -= 1;
                y = Math.max(0, y);
                break;
            case East:
                x += 1;
                x = Math.min(maxX, x);
                break;
            case South:
                y += 1;
                y = Math.min(maxY, y);
                break;
            case West:
                x -= 1;
                x = Math.max(0, x);
                break;
        }
        postitions.add(x + " " + y);
    }


}
