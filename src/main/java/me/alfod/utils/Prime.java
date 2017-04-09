package me.alfod.utils;

/**
 * Created by arvin
 */
public class Prime {
    public static int getNext(int start) {
        if (start % 2 == 0) {
            start += 1;
        }
        while (!isPrime(start)) {
            start += 2;
        }
        return start;
    }

    public static boolean isPrime(int number) {
        if (number % 2 == 0) return false;
        int dividend = 3;
        int upperLimit = (int) Math.sqrt(number) + 1;
        while (dividend < upperLimit) {
            if (number % dividend == 0) return false;
            dividend += 2;
        }
        return true;
    }
}
