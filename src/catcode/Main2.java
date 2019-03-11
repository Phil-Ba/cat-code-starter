package catcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main2 {

    public static void main(String[] args) {
        Inputparser parser = new Inputparser("level2/level2_0.in");
        int segmente = parser.scanLine().nextInt();
        int amountCars = parser.scanLine().nextInt();
        List<Scanner> carLines = parser.scanLines(amountCars, ",");
        List<Car> cars = carLines.stream()
                .map(s -> new Car(s.nextInt(), s.nextInt(), s.nextInt()))
                .collect(Collectors.toList());

        ArrayList<Car> oricars = new ArrayList<>(cars);
        ArrayList<Car> activeCars = new ArrayList<>();

        List<Integer> prevPostions = new ArrayList<>();

        int ticks = 1;
        while (cars.isEmpty() == false) {
            //
            for (Car oricar : oricars) {
                if (oricar.startTime >= ticks && oricar.isFinished() == false && activeCars.contains(oricar) == false) {
                    if (prevPostions.contains(oricar.currentPosition - 1) || prevPostions.contains(oricar.currentPosition)) {
                        continue;
                    }
                    activeCars.add(oricar);
                }
            }

            //save current postions
            prevPostions = activeCars.stream()
                    .map(car -> car.currentPosition)
                    .collect(Collectors.toList());

            for (Car car : new ArrayList<>(cars)) {
                //check if finished
                boolean blocked = prevPostions.contains(car.currentPosition + 1);
                if (car.isFinished()) {
                    car.isLeaving = true;
                } else if (car.isLeaving) {
                    car.isLeaving = false;
                    car.hasLeft = true;
                    car.finishTime = ticks;
                    cars.remove(car);
                }
                if (!blocked) {
                    car.move();
                }
            }

            ticks++;
        }
        System.out.println(oricars.stream().map(car -> car.finishTime + "").collect(Collectors.joining(",")));
    }

}
