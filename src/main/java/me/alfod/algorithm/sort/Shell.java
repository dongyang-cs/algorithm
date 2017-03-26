package me.alfod.algorithm.sort;

/**
 * Created by Arvin Alfod on 2016/11/5.
 */

import java.util.LinkedList;
import java.util.List;

public class Shell {
    private static int[] getShellIncrementArrayByName(String incrementName, int arrayLength) {
        switch (incrementName) {
            case "Sedgewick": {
                int i = 1, j = 1;
                List<Integer> linkedList = new LinkedList<>();
                while (i < arrayLength / 2) {
                    linkedList.add(i);
                    j++;
                    i = (int) (Math.pow(4, j) - 3 * Math.pow(2, j) + 1);
                }
                Integer[] a = linkedList.toArray(new Integer[linkedList.size()]);
                int[] b = new int[linkedList.size()];
                for (i = 0; i < b.length; i++) {
                    b[i] = a[i];
                }
                return b;
            }
        }

        return null;
    }


    public static Integer[] shell(Integer[] array) {
        int a[] = getShellIncrementArrayByName("Sedgewick", array.length);
        int j_tmp, current_in_array;
        for (int i = a.length - 1; i >= 0; i--) {
            for (int j = 1; j <= ((array.length - 1) / a[i]); j++) {
                j_tmp = j;
                current_in_array = array[j * a[i]];
                while ((j_tmp - 1) >= 0 && array[(j_tmp - 1) * a[i]] > current_in_array) {
                    array[j_tmp * a[i]] = array[(j_tmp - 1) * a[i]];
                    --j_tmp;
                }
                array[j_tmp * a[i]] = current_in_array;
            }
        }

        return array;

    }
}
