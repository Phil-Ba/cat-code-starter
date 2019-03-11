package catcode.level2;

import catcode.Inputparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main2 {

    public static void main(String[] args) {
        Inputparser parser = new Inputparser("level2/level2_1.in");
        int segmente = parser.scanLine().nextInt();
        int amountCars = parser.scanLine().nextInt();
        List<Scanner> carLines = parser.scanLines(amountCars, ",");
        List<Car> cars = carLines.stream()
                .map(s -> new Car(s.nextInt(), s.nextInt(), s.nextInt()))
                .collect(Collectors.toList());

        ArrayList<Car> oricars = new ArrayList<>(cars);
        ArrayList<Car> activeCars = new ArrayList<>();

        List<Integer> occupiedPostions = new ArrayList<>();

        int currentTick = 1;
        while (oricars.stream().allMatch(car -> car.state == Car.CarState.HasLeft) == false) {
            ArrayList<Integer> previousPositions = new ArrayList<>();
            //check if car can start
            for (Car oricar : oricars) {
                if (oricar.startTime <= currentTick && oricar.state == Car.CarState.Waiting) {
                    if (occupiedPostions.contains(oricar.currentPosition - 1) || occupiedPostions.contains(oricar.currentPosition)) {
                        continue;
                    }
                    oricar.state = Car.CarState.Entering;
                    activeCars.add(oricar);
                }
            }

            //save current postions

//            occupiedPostions = activeCars.stream()
//                    .map(car -> car.currentPosition)
//                    .collect(Collectors.toList());

            for (Car car : new ArrayList<>(activeCars)) {
                boolean blocked = occupiedPostions.contains(car.currentPosition + 1);
                switch (car.state) {
                    case Waiting:
                        throw new IllegalStateException();
                    case Entering:
                        previousPositions.add(car.currentPosition);
                        car.state = Car.CarState.Driving;
                        break;
                    case Driving:
                        previousPositions.add(car.currentPosition);
                        //check if finished
                        if (car.isFinished()) {
                            car.finishTime = currentTick;
                            car.state = Car.CarState.Leaving;
                            break;
                        } else if (!blocked) {
                            car.move();
                        }
                        break;
                    case Leaving:
                        previousPositions.add(car.currentPosition);
                        car.state = Car.CarState.HasLeft;
                        activeCars.remove(car);
                        break;
                    case HasLeft:
                        //no longer in previous positions
                        break;
                }
            }
            occupiedPostions = previousPositions;
            currentTick++;
        }
        System.out.println(oricars.stream().
                map(car -> car.finishTime + "").
                collect(Collectors.joining(",")));
    }

}
