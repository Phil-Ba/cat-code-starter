package catcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class IOUtils {
    
    public static Stream<String> readFile(String file) {
        try {
            return Files.lines(Paths.get(IOUtils.class.getResource(file).toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Scanner scanFile(String file) {
        try {
            return new Scanner(Paths.get(IOUtils.class.getResource(file).toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readLines(Scanner sc, int lines) {
        List<String> readLines = new ArrayList<>();
        for (int i = 0; i < lines; i++) {
            readLines.add(sc.nextLine());
        }
        return readLines;
    }

    public static String[] readLinesArr(Scanner sc, int lines) {
        String[] readLines = new String[lines];
        for (int i = 0; i < lines; i++) {
            readLines[i] = sc.nextLine();
        }
        return readLines;
    }

    public static List<String> readTokens(Scanner sc, int tokens) {
        List<String> readTokens = new ArrayList<>();
        for (int i = 0; i < tokens; i++) {
            readTokens.add(sc.next());
        }
        return readTokens;
    }

    public static String[] readTokensArr(Scanner sc, int tokens) {
        String[] readTokens = new String[tokens];
        for (int i = 0; i < tokens; i++) {
            readTokens[i] = sc.next();
        }
        return readTokens;
    }

    public static List<String> readFileAsList(String file) {
        return readFile(file).collect(Collectors.toList());
    }

    public static void write(String file, List<String> lines) {
        try {
            Files.write(Paths.get("src/catcode/", file), lines);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void write(String file, String... lines) {
        write(file, Arrays.asList(lines));
    }
    
    
}
