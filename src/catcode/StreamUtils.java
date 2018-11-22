package catcode;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtils {
    
    <T> Map<T, Long> countBy(Stream<T> s) {
        return countBy(s, Function.identity());
    }
    
    <T, R> Map<R, Long> countBy(Stream<T> s, Function<T, R> keyExtractor) {
        return s.collect(Collectors.groupingBy(keyExtractor, Collectors.counting()));
    }
    
}
