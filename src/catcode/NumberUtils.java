package catcode;

public class NumberUtils {
    
    static double format(double in, int decimals) {
        double pow = Math.pow(10.0, decimals);
        return (double) Math.round(in * pow )/ pow;
    }
    
    static float format(float in, int decimals) {
        double pow = Math.pow(10.0, decimals);
        return (float) (Math.round(in * pow )/ pow);
    }
    
}
