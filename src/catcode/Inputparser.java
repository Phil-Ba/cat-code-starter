package catcode;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Inputparser {
    
    private final Scanner lineScanner;
    
    public Inputparser(String file) {
        try {
            lineScanner = new Scanner(Paths.get(IOUtils.class.getResource(file).toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public Inputparser(String file, String delimiter) {
        this(file);
        lineScanner.useDelimiter(delimiter);
    }
    
    public void useDelimiter(String delimiter) {
        lineScanner.useDelimiter(delimiter);
    }
    
    public String readLine() {
        return lineScanner.nextLine();
    }
    
    public List<String> readLines(int lines) {
        List<String> lins = new ArrayList<>(lines);
        for (int j = 0; j < lines; j++) {
            lins.add(readLine());
        }
        return lins;
    }
    
    public Scanner scanLine() {
        return new Scanner(readLine());
    }
    
    public Scanner scanLine(String delimiter) {
        return new Scanner(readLine()).useDelimiter(delimiter);
    }
    
    public List<Scanner> scanLines(int lines) {
        List<Scanner> lins = new ArrayList<>(lines);
        for (int j = 0; j < lines; j++) {
            lins.add(scanLine());
        }
        return lins;
    }
    
    public List<Scanner> scanLines(int lines, String delimiter) {
        List<Scanner> lins = new ArrayList<>(lines);
        for (int j = 0; j < lines; j++) {
            lins.add(scanLine(delimiter));
        }
        return lins;
    }
}
