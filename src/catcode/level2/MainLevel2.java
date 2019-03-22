package catcode.level2;

import catcode.IOUtils;

import java.util.Scanner;

/**
 *
 */
public class MainLevel2 {

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            Scanner scanner = IOUtils.scanFile("level2/level2_" + i + ".in");
            solve(scanner, i);
        }
    }

    static void solve(Scanner scanner, int i) {
        int maxX = scanner.nextInt();
        int maxY = scanner.nextInt();
        Invader invader = new Invader(scanner.nextInt(), scanner.nextInt(),maxX, maxY);
        while (scanner.hasNext()) {
            String command = scanner.next();
            int times = scanner.nextInt();
            switch (command) {
                case "T":
                    invader.turn(times);
                    break;
                case "F":
                    invader.move(times);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        IOUtils.write("level2/level2_" + i + ".out", invader.postitions);
    }

}
