package catcode;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 */
public class MainLevel1 {
    
    public static void main(String[] args) {
//        System.out.println(IOUtils.readFileAsList("test.txt"));
        Scanner scanner = IOUtils.scanFile("test.txt");
        System.out.println(scanner.next());
        System.out.println(scanner.nextLine());
        scanner.forEachRemaining(s -> System.out.println(s));
        System.out.println(scanner);
//        IOUtils.write("out.txt", "1","2","3");
//        solve1(IOUtils.readFile("level1.txt"));
    }
    
    static void solve1(Stream<String> input) {
    
    }
    
}
