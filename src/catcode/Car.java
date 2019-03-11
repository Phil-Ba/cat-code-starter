package catcode;

public class Car {

    int start, end, startTime, currentPosition, finishTime=-1;
    boolean isLeaving=false;
    boolean hasLeft=false;

    public Car(int start, int end, int startTime) {
        currentPosition = start;
        this.start = start;
        this.end = end;
        this.startTime = startTime;
    }

    public void move(){
        currentPosition++;
    }
     public boolean isFinished(){
         return currentPosition == end;
     }

}
