package catcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class MainLevel3 {
    
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
    
    static class Coord {
        
        int x, y = 0;
        
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static void solve1(String input) {
        String[] commandArr = input.split(" ");
        List<String> strings = Arrays.stream(commandArr)
                .collect(Collectors.toList());
        int commands = Integer.parseInt(commandArr[0]);
        List<String> expCommands = new ArrayList<>();
        for (int i = 1; i <= commands * 2; ) {
            String currComm = commandArr[i++];
            int times = Integer.parseInt(commandArr[i++]);
            for (int j = 0; j < times; j++) {
                expCommands.add(currComm);
            }
        }
        final int f = 'F';
        final int l = 'L';
        final int r = 'R';
        System.out.println(expCommands);
        long count = expCommands.stream()
                .flatMap(s -> s.chars().boxed())
                .filter(c -> c.equals(f))
                .count();
        List<Integer> moves = expCommands.stream()
                .flatMap(s -> s.chars().boxed())
                .collect(Collectors.toList());
        
        final int n = 'n';
        final int e = 'e';
        final int s = 's';
        final int w = 'w';
        int currDir = 'n';
        int xMax = 0;
        int xMin = 0;
        int yMax = 0;
        int yMin = 0;
        int x = 0;
        int y = 0;
        List<Coord> coords = new ArrayList<>();
        coords.add(new Coord(0, 0));
        for (Integer move : moves) {
            switch (move) {
                case f:
                    switch (currDir) {
                        case n:
                            y++;
                            yMax = y > yMax ? y : yMax;
                            break;
                        case e:
                            x++;
                            xMax = x > xMax ? x : xMax;
                            break;
                        case s:
                            y--;
                            yMin = y < yMin ? y : yMin;
                            break;
                        case w:
                            x--;
                            xMin = x < xMin ? x : xMin;
                            break;
                    }
                    break;
                case l:
                    switch (currDir) {
                        case n:
                            currDir = w;
                            break;
                        case e:
                            currDir = n;
                            break;
                        case s:
                            currDir = e;
                            break;
                        case w:
                            currDir = s;
                            break;
                    }
                    break;
                case r:
                    switch (currDir) {
                        case n:
                            currDir = e;
                            break;
                        case e:
                            currDir = s;
                            break;
                        case s:
                            currDir = w;
                            break;
                        case w:
                            currDir = n;
                            break;
                    }
                    break;
            }
            coords.add(new Coord(x, y));
        }
        
        int m1 = Math.abs(xMin) + Math.abs(xMax);
        int m2 = Math.abs(yMin) + Math.abs(yMax);
        
        int ar = 0;
        for (int i = 1; i <= coords.size() - 1; i++) {
            Coord next = coords.get(i);
            Coord prev = coords.get(i - 1);
            ar += prev.x * next.y - next.x * prev.y;
        }
        Coord prev = coords.get(0);
        Coord next = coords.get(coords.size() - 1);
        
        ar += next.x * prev.y - prev.x * prev.y;
        
        ar = Math.abs(ar) / 2;
        System.out.println(count + " " + m1 * m2 + " " + ar + " " + (count - ar)
        );
    }
    
}
