package catcode.level1;

import catcode.IOUtils;

import java.util.Scanner;

/**
 *
 */
public class MainLevel1 {
    
    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            Scanner scanner = IOUtils.scanFile("level1/level1_" + i + ".in");
            solve(scanner, i);
        }
    }

    static void solve(Scanner scanner, int i) {
        Invader invader = new Invader(scanner.nextInt(), scanner.nextInt());
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
            }
        }
        IOUtils.write("level1/level1_" + i + ".out",invader.x + " " + invader.y);
        System.out.println(invader.x + " " + invader.y);
    }
    
}
