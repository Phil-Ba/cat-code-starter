package catcode.level1;

import catcode.IOUtils;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 */
public class MainLevel1 {
    
    public static void main(String[] args) {
        Scanner scanner = IOUtils.scanFile("level1/");
        solve(scanner);
    }
    
    static void solve(Scanner scanner) {
        Invader invader = new Invader(scanner.nextInt(), scanner.nextInt());
//        scanner.nextLine()
    }
    
}
