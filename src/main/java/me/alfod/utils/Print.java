package me.alfod.utils;

/**
 * Created by arvin
 */
public class Print {

    private static void print(Object o) {
        System.out.print(o);
    }


    private static void println(Object[] o) {
        println();
        print("Array[");
        for (int i = 0; i < o.length; i++) {
            print(o[i]);
            if (i < o.length - 1)
                print(" ,");
        }
        print("]");
        println();
    }

    private static void println(Object o) {
        System.out.println(o);
    }

    private static void println() {
        System.out.println();
    }

    //endregion

}
