package com.company;

public class Main {

    public static void main(String[] args) {
        int[] a = new int[1000];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * a.length);
        }
        printArray(a);
        //quickSort(a,0,a.length,0);
    }

    public static void byInsert(int a[]) {
        int i, j, k;
        int count = 0;
        for (i = 1; i < a.length; i++) {
            k = a[i];
            for (j = i - 1; j >= 0 && a[j] > a[j + 1]; j--) {
                a[j + 1] = a[j];
                a[j] = k;
                count++;
            }
        }
        printArray(a);

        System.out.println("\n Count: " + count);
    }

    static void byLogInsert(int[] a) {
        int i, j, k;
        int count = 0;
        for (i = 1; i < a.length; i++) {
            k = a[i];
            j = i;

            for (j = i - 1; j >= 0 && a[j] > a[j + 1]; j--) {
                a[j + 1] = a[j];
                a[j] = k;
                count++;
            }
        }
        printArray(a);
    }

    static void printArray(int[] a) {
        for (int l = 0; l < a.length; l++)
            System.out.print(" " + a[l]);
        System.out.println("\n Finish");
    }

    static int count=0;

    public static void quickSort(Integer[] a, int start, int end, int deep) {

        if (end - start < 2)
            return;
        int i, j, k;
        Integer tmp[] = new Integer[end - start];
        System.arraycopy(a, start, tmp, 0, tmp.length);
        int tmp_start = start, tmp_end = end - 1;
        j = a[start];
        for (i = 0; i < tmp.length; i++) {
            if (tmp[i] < j) {
                a[tmp_start] = tmp[i];
                tmp_start++;
            }
            if (tmp[i] > j) {
                a[tmp_end] = tmp[i];
                tmp_end--;
            }
            count++;
        }
        if (tmp_end +1- tmp_start+1 > 1) {
            for (i = tmp_start; i < tmp_end+1; i++) {
                a[i] = j;
                count++;
            }
        }

        quickSort(a, start, tmp_start, deep+1);
        quickSort(a, tmp_end+1, end, deep+1);
        if (deep == 0) {
            //printArray(a);
            System.out.println("\nquickSort Count: "+count);
        }
    }
}
