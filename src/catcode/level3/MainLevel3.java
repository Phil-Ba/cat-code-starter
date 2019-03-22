package catcode.level3;

import catcode.IOUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 */
public class MainLevel3 {

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            Scanner scanner = IOUtils.scanFile("level3/level3_" + i + ".in");
            solve(scanner, i);
        }
    }

    static void solve(Scanner scanner, int i) {
        int maxX = scanner.nextInt();
        int maxY = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        scanner.nextLine();
        String commands = scanner.nextLine();
        double speed = scanner.nextDouble();

        int aliensNumber = scanner.nextInt();
        HashMap<Integer, Invader> invadersById = new HashMap<>();
        for (int j = 0; j < aliensNumber; j++) {
            Invader invader = new Invader(x, y, maxX, maxY, speed, scanner.nextInt(), j);
            invadersById.put(j, invader);
        }

        int queriesAmount = scanner.nextInt();
        scanner.nextLine();
        List<String> queries = new ArrayList<>();
        for (int j = 0; j < queriesAmount; j++) {
            queries.add(scanner.nextLine());
        }
        List<String> results = queries.stream().map(q -> {
            Scanner qScanner = new Scanner(q);
            Scanner commandsScanner = new Scanner(commands);
            int amountTicks = qScanner.nextInt();
            int invaderID = qScanner.nextInt();
            Invader invader = invadersById.get(invaderID);
            //TODO check <=
            for (int j = 0; j <= amountTicks; j++) {
                if (j <= invader.spawnTime) {
                    continue;
                }
                executeMove(invader, commandsScanner);
            }
            return amountTicks + " " + invaderID + " " + ((int) (Math.floor(invader.x))) + " " + ((int) Math.floor(invader.y));
        }).collect(Collectors.toList());

        IOUtils.write("level3/level3_" + i + ".out", results);
    }

    static void executeMove(Invader invader, Scanner commands) {
        while (commands.hasNext()) {
            String command = commands.next();
            int times = commands.nextInt();
            switch (command) {
                case "T":
                    invader.turn(times);
                    break;
                case "F":
                    invader.move(times);
                    return;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

}
