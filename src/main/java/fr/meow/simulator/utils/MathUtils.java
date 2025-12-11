package fr.meow.simulator.utils;

public class MathUtils {
    public static double jitter(double maxFraction) {
        return (Math.random() * (2 * maxFraction)) - maxFraction;
    }

    public static int randomInt(int min, int maxInclusive) {
        return (int) (Math.random() * (maxInclusive - min + 1)) + min;
    }

    public static double roundTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public static double gaussian() {
        // Box-Muller transform to approximate N(0,1).
        double u1 = Math.random();
        double u2 = Math.random();
        return Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);
    }
}
