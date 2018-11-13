package catcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class MainLevel2 {
    
    static class Pair implements Comparable<Pair> {
        int x, y;
        
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(Pair pair) {
            return Integer.compare(x, pair.x);
        }
        
        @Override
        public String toString() {
            return x + " " + y;
        }
    }
    
    public static void main(String[] args) {
//        System.out.println(IOUtils.readFileAsList("test.txt"));
//        IOUtils.write("out.txt", "1","2","3");
        solve2("193 125 133 134 135 136 -52 -51 -50 -49 -48 -47 -46 -45 66 67 68 69 70 71 -38 -37 -36 -35 -34 -33 -32 -31 -30 -29 -132 -131 -130 -193 -192 -191 -190 -189 -188 -187 -186 -185 -184 -183 -182 -181 -180 -179 -178 -177 -176 -175 -174 -173 -172 -171 -170 -169 -77 -76 -75 -74 -73 -72 18 19 20 21 22 23 24 25 26 27 28 -164 -163 -65 -64 -63 -62 -61 -60 -59 -58 -57 -56 -55 -54 -53 39 40 41 42 43 44 159 160 161 162 -17 -16 -15 -14 -13 -12 -11 -10 -9 -8 -7 -6 -5 -4 -3 -2 -1 -168 -167 -166 -165 126 127 128 129 86 87 88 89 90 91 92 93 94 95 96 -124 -123 -122 -121 -120 -119 -118 -117 -116 -115 -114 -113 -112 -111 -110 -109 -108 -107 -106 -105 -104 -103 -102 -101 -100 -99 -98 -97 153 154 155 156 157 158 -148 -147 -146 -145 -144 -143 -142 -141 -140 -139 -138 -137 -85 -84 -83 -82 -81 -80 -79 -78 -152 -151 -150 -149 -45 12 44 94");
    }
    
    static void solve2(String input) {
        List<Integer> ints = Arrays.stream(
                input.split(" ")
        ).map(i -> Integer.parseInt(i))
                .collect(Collectors.toList());
        Integer permuatations = ints.remove(0);
        
        Integer[] permutationsArr = ints.subList(0, permuatations)
                .toArray(new Integer[permuatations]);
        
        Integer[] pairs = ints.subList(permuatations, ints.size())
                .toArray(new Integer[4]);
        
        int i = pairs[1] ;
        int j = pairs[3] ;
        if (pairs[0] + pairs[2] == 1) {
            Integer[] toInvert = Arrays.copyOfRange(permutationsArr, i, j - 1+1);
            toInvert = ArrayUtils.invert(toInvert);
            for (int k = 0; k < toInvert.length; k++) {
                permutationsArr[i + k] = toInvert[k] * -1;
            }
        } else {
            Integer[] toInvert = Arrays.copyOfRange(permutationsArr, i + 1, j+1);
            toInvert = ArrayUtils.invert(toInvert);
            for (int k = 0; k < toInvert.length; k++) {
                permutationsArr[i+1 + k] = toInvert[k] * -1;
            }
        }
    
        System.out.println(Arrays.toString(permutationsArr).replaceAll("[\\]\\[,]",""));
    }
    
}