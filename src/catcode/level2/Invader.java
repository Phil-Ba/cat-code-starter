package catcode.level2;

import java.util.ArrayList;
import java.util.List;

public class Invader {

    static enum Direction {
        North, East, South, West;
    }

    int x;
    int y;
    private final int maxX;
    private final int maxY;
    Direction direction = Direction.East;
    List<String> postitions = new ArrayList<>();

    public Invader(int x, int y, int maxX, int maxY) {
        this.x = x;
        this.y = y;
        this.maxX = maxX;
        this.maxY = maxY;
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


}
