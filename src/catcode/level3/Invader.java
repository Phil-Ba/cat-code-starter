package catcode.level3;

import java.util.ArrayList;
import java.util.List;

public class Invader {

    static enum Direction {
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
        this.maxX = maxX;
        this.maxY = maxY;
        this.spawnTime = spawnTime;
        this.speed = speed;
        this.id = id;
        postitions.add(x + " " + y);
    }

    void turn(int times) {
        for (int i = 0; i < times; i++) {
            switch (direction) {
                case North:
                    direction = Direction.East;
                    break;
                case East:
                    direction = Direction.South;
                    break;
                case South:
                    direction = Direction.West;
                    break;
                case West:
                    direction = Direction.North;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

    }

    void move(int times) {
        for (int i = 0; i < times; i++) {
            switch (direction) {
                case North:
                    y -= speed;
                    y = Math.max(0, y);
                    break;
                case East:
                    x += speed;
                    x = Math.min(maxX, x);
                    break;
                case South:
                    y += speed;
                    y = Math.min(maxY, y);
                    break;
                case West:
                    x -= speed;
                    x = Math.max(0, x);
                    break;
            }
            postitions.add(x + " " + y);
        }
    }


}
