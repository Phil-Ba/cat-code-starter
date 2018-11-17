package catcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class MainLevel1 {
    
    public static void main(String[] args) {
//        System.out.println(IOUtils.readFileAsList("test.txt"));
//        IOUtils.write("out.txt", "1","2","3");
//        solve1(IOUtils.readFile("level1.txt"));
        solve1("1 FFFR 4");
        solve1("9 F 6 R 1 F 4 RFF 2 LFF 1 LFFFR 1 F 2 R 1 F 5");
        solve1("14 L 1 FR 1 FFFFFL 1 FFFFL 1 F 12 L 1 F 12 L 1 F 12 L 1 FFFFL 1 FFFFFFFFR 1 FFFR 1 FFFL 1");
        solve1("32 FFRFLFLFFRFRFLFF 3 R 1 FFLFRFRFLFFF 3 R 1 FFFFFF 3 L 1 FFFRFLFLFRFF 2 R 1 FFFRFLFLFRFF 3 R 1 FFFFFF 1 L 1 FFRFLFLFFRFRFLFF 3 R 1 FFLFRFRFFLFLFRFF 2 L 1 FFLFRFRFFLFLFRFF 3 R 1 FFRFLFLFFRFRFLFF 2 R 1 FFRFLFLFFRFRFLFF 2 L 1 FFFFFF 3 R 1 FFFRFLFLFRFF 5 R 1 FFLFRFRFLFFF 1 L 1 FFLFRFRFFLFLFRFF 2 R 1 FFRFLFLFFRFRFLFF 2 L 1");
        solve1("10 FFLFRFRFFLFLFRFF 5 L 1 FFFRFLFLFRFF 4 L 1 FFLFRFRFFLFLFRFF 8 L 1 FFLFRFRFFLFLFRFF 4 L 1 FFFFFF 3 R 1 ");
    }
    
    static void solve1(String input) {
        String[] commandArr = input.split(" ");
        List<String> strings = Arrays.stream(commandArr)
                .collect(Collectors.toList());
        int commands = Integer.parseInt(commandArr[0]);
        List<String> expCommands = new ArrayList<>();
        for (int i = 1; i <= commands*2; ) {
            String currComm = commandArr[i++];
            int times = Integer.parseInt(commandArr[i++]);
            for (int j = 0; j < times; j++) {
                expCommands.add(currComm);
            }
        }
        int f = 'F';
        System.out.println(expCommands);
        long count = expCommands.stream()
                .flatMap(s -> s.chars().boxed())
                .filter(c -> c.equals(f))
                .count();
        System.out.println(count);
    }
    
}
