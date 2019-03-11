package catcode.level2;

import catcode.Inputparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main2 {

    public static void main(String[] args) {
//        solve("level2/level2_0.in",);
        solve("level2/level2_1.in", true);
        solve("level2/level2_1.in", false);
//        solve("level2/level2_5.in");
//        solve("level2/level2_6.in");
    }

    private static void solve(String file, boolean brk) {
        Inputparser parser = new Inputparser(file);
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
                        //check if finished
                        if (car.isFinished()) {
                            car.finishTime = currentTick;
                            car.state = Car.CarState.HasLeft;
//                            car.state = Car.CarState.Leaving;
                            activeCars.remove(car);
                            if (brk) {
                                break;
                            }
                        } else if (!blocked) {
                            car.move();
                        }
                        previousPositions.add(car.currentPosition);
                        break;
                    case Leaving:
//                        previousPositions.add(car.currentPosition);
//                        car.state = Car.CarState.HasLeft;
//                        activeCars.remove(car);
                        break;
                    case HasLeft:
                        //no longer in previous positions
                        break;
                }
            }
            String out = "";
            for (int i = 0; i < segmente; i++) {
                int finalI = i;
                int finalCurrentTick = currentTick;
                out += cars.stream()
                        .filter(car -> car.currentPosition == finalI + 1)
                        .findFirst()
                        .map(car -> {
                            switch (car.state) {
                                case Waiting:
                                    return null;
                                case Entering:
                                    return "E";
                                case Driving:
                                    return "D";
                                case Leaving:
                                    return "L";
                                case HasLeft:
                                    return car.finishTime == finalCurrentTick ? "L" : null;
                                default:
                                    return null;
                            }
                        }).orElse("-");
            }
            System.out.println(out);
            occupiedPostions = previousPositions;
            currentTick++;
        }
        System.out.println(oricars.stream().
                map(car -> car.finishTime + "").
                collect(Collectors.joining(",")));
    }

}
