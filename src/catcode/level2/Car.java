package catcode.level2;


public class Car {

    enum CarState {
        Waiting, Entering, Driving, Leaving, HasLeft
    }

    int start, end, startTime, currentPosition, finishTime = -1;
    CarState state = CarState.Waiting;

    public Car(int start, int end, int startTime) {
        currentPosition = start;
        this.start = start;
        this.end = end;
        this.startTime = startTime;
    }

    public void move() {
        currentPosition++;
    }

    public boolean isFinished() {
        return currentPosition == end;
    }

}
