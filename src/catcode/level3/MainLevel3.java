package catcode.level3;

import catcode.IOUtils;
import catcode.level3.Invader.Direction;

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
        for (int i = 0; i < 5; i++) {
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
        Scanner commandsScanner = new Scanner(commands);
        List<Direction> moves = parseCommands(commandsScanner);
        List<String> results = queries.stream().map(q -> {
            Scanner qScanner = new Scanner(q);
            int queryTicks = qScanner.nextInt();
            int invaderID = qScanner.nextInt();
            //TODO
            Invader invader = invadersById.get(invaderID);
            int amountTicks = queryTicks - invader.spawnTime;
            int amountMoves = (int) (Math.floor(amountTicks * speed));

//            for (int j = 0; j < amountTicks; j++) {
//                if (j <= invader.spawnTime) {
//                    continue;
//                }
//                if (moves.isEmpty() == false) {
//                    invader.move(moves.remove(0));
//                }
//            }

            List<Direction> subMoves = moves.subList(0, Math.min(amountMoves, moves.size()));
            for (Direction subMove : subMoves) {
                invader.move(subMove);
            }
            String result = queryTicks + " " + invaderID + " " + ((int) (Math.floor(invader.x))) + " " + ((int) Math.floor(invader.y));
            invader.reset();
            return result;
        }).collect(Collectors.toList());

        IOUtils.write("level3/level3_" + i + ".out", results);
    }

    private static List<Direction> parseCommands(Scanner commandsScanner) {
        List<Direction> moves = new ArrayList<>();
        Direction direction = Direction.East;
        while (commandsScanner.hasNext()) {
            String command = commandsScanner.next();
            int times = commandsScanner.nextInt();
            switch (command) {
                case "T":
                    direction = turn(direction, times);
                    break;
                case "F":
                    for (int i = 0; i < times; i++) {
                        moves.add(direction);
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return moves;
    }

    static Direction turn(Direction current, int times) {
        Direction temp = current;
        for (int i = 0; i < times; i++) {
            switch (temp) {
                case North:
                    temp = Direction.East;
                    break;
                case East:
                    temp = Direction.South;
                    break;
                case South:
                    temp = Direction.West;
                    break;
                case West:
                    temp = Direction.North;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return temp;
    }


}
