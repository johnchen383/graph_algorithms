package utils;

public class InfInteger {
    public static String toString(Integer i) {
        if (i == Integer.MAX_VALUE) return "INF";
        if (i == Integer.MIN_VALUE) return "-INF";
        
        return Integer.toString(i);
    }
}
