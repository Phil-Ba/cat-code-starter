package catcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
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
    
    public static List<String> readFileAsList(String file) {
        return readFile(file).collect(Collectors.toList());
    }
    
    public static void write(String file, List<String> lines) {
        try {
            Files.write(Paths.get("src/catcode/", file), lines, StandardOpenOption.CREATE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void write(String file, String... lines) {
        write(file, Arrays.asList(lines));
    }
    
    
}
