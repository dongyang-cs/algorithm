package me.alfod.algorithm.sort;


import java.lang.reflect.Array;

public class Quick {
    /**
     * @param inputArray array going to be sorted
     * @param <T>        extends Comparable
     * @return sorted array
     */
    public static <T extends Comparable<? super T>> T[] sort(T[] inputArray) {
        return sort(inputArray, null, 0, inputArray.length);
    }

    /**
     * @param inputArray array going to be sorted
     * @param tmpArray   temporary array used as storage
     * @param HEAD       start index of array
     * @param TAIL       end index of array
     * @param <T>        extends Comparable
     * @return sorted array
     */
    @SuppressWarnings(value = "unchecked")
    public static <T extends Comparable<? super T>> T[] sort(T[] inputArray, T[] tmpArray, final int HEAD, final int TAIL) {
        if (tmpArray == null) {
            tmpArray = (T[]) Array.newInstance(Comparable.class, inputArray.length);
        }

        final T axle = inputArray[HEAD];
        int tmpHead = HEAD, tmpTail = TAIL - 1;
        for (int i = HEAD; i < TAIL; i++) {
            if (inputArray[i].compareTo(axle) > 0) {
                tmpArray[tmpTail--] = inputArray[i];
            }
            if (inputArray[i].compareTo(axle) < 0) {
                tmpArray[tmpHead++] = inputArray[i];
            }
        }


        for (int i = HEAD; i < TAIL; i++) {
            if (i >= tmpHead && i <= tmpTail) {
                inputArray[i] = axle;
            } else {
                inputArray[i] = tmpArray[i];
            }
        }

        if (tmpHead - HEAD > 2) {
            inputArray = sort(inputArray, tmpArray, HEAD, tmpHead);
        }

        if (TAIL - tmpTail - 1 > 2) {
            inputArray = sort(inputArray, tmpArray, tmpTail + 1, TAIL);
        }


        return inputArray;
    }

}
