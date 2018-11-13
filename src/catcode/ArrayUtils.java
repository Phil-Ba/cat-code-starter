package catcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArrayUtils {
    
    static int[] toArray(Collection<Integer> col) {
        return col.stream()
                .mapToInt(i -> i)
                .toArray();
    }
    
    static List<Integer> toList(int[] arr) {
        List<Integer> l= new ArrayList<>();
        for (int i : arr) {
            l.add(i);
        }
        return l;
    }
    
    static int[][] initPlot(int rows, int cols) {
        int[][] plot = new int[rows][cols];
        int i = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                plot[r][c] = i++;
            }
        }
        return plot;
    }
    
    static int[] vslice(int[][] arr, int col) {
        int rows = arr.length;
        int[] slice = new int[rows];
        for (int i = 0; i < rows; i++) {
            slice[i] = arr[i][col];
        }
        return slice;
    }
    
    static int[] invert(int[] arr) {
        int rows = arr.length;
        int[] slice = new int[rows];
        int j = 0;
        for (int i = arr.length - 1; i > -1; i--) {
            slice[j++] = arr[i];
        }
        return slice;
    }
    
    static <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;
        
        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        
        return c;
    }
    
    static Integer[] invert(Integer[] arr) {
        int rows = arr.length;
        Integer[] slice = new Integer[rows];
        int j = 0;
        for (int i = arr.length - 1; i > -1; i--) {
            slice[j++] = arr[i];
        }
        return slice;
    }
    
    static int[] hslice(int[][] arr, int row) {
        int cols = arr[row].length;
        int[] slice = new int[cols];
        for (int i = 0; i < cols; i++) {
            slice[i] = arr[row][i];
        }
        return slice;
    }
    
}
