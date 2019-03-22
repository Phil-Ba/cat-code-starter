package catcode.level1;

public class Invader {

    static enum Direction{
        North,East,South,West;
    };

    int x,y;
    Direction direction = Direction.East;

    public Invader(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void turn(int times){
        for (int i = 0; i < times; i++) {
            switch (direction){
                case North:
                    direction = Direction.East;
                    break;
                case East:
                    direction=Direction.South;
                    break;
                case South:
                    direction=Direction.West;
                    break;
                case West:
                    direction=Direction.North;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

    }

    void move(int times){
        switch (direction){
            case North:
                y -= times;
                y = Math.max(0, y);
                break;
            case East:
                x += times;
                break;
            case South:
                y += times;
                break;
            case West:
                x -= times;
                x = Math.max(0, x);
                break;
        }
    }


}
