package catcode;

import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Example {
    
    public static void main(String[] args) {
        Inputparser parser = new Inputparser("example/test.txt");
        //amountStringTokens:int [stringTokens]
        int linesType1 = parser.scanLine().nextInt();
        List<Scanner> lines = parser.scanLines(linesType1, " ");
        for (Scanner line : lines) {
            System.out.println("\r\nLine contains: ");
            int strings = line.nextInt();
            for (int i = 0; i < strings; i++) {
                System.out.println(line.next());
            }
        }
        //amountStringTokens:int,[stringTokens],amountDoubleTokens,[doubleTokens]
        int linesType2 = parser.scanLine().nextInt();
        lines = parser.scanLines(linesType2, ",");
        for (Scanner line : lines) {
            System.out.println("\r\nLine contains: ");
            int strings = line.nextInt();
            for (int i = 0; i < strings; i++) {
                System.out.println(line.next());
            }
            int doubles = line.nextInt();
            for (int i = 0; i < doubles; i++) {
                System.out.println(line.nextDouble());
            }
        }
    }
    
}
