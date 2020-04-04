package com.algorithm;

public class QuickSort {
    public static void main(String[] args) {
        int[] origin = {45, 21, 56, 99, 22, 44, 89, 45, 45, 27, 1, 22, 22, 4, 94, 99, 35, 56, 2};
//        int[] origin = {45, 21, 56, 22};
//        int[] origin = {2, 1, 3};

        quickSort(origin, 0, origin.length - 1);

        for (int i = 0; i < origin.length; i++) {
            System.out.print(origin[i] + " ");
        }
    }

    public static void quickSort(int[] array, int i, int j) {
        int first = i;
        int last = j;
        if (i == j) {
            return;
        }

        int index = i;
        int temp = array[index];

        while (i != j) {
            while (array[index] <= array[j] && i != j) {
                j -= 1;
            }

            array[index] = array[j];
            array[j] = temp;
            index = j;

            while (array[index] >= array[i] && i != j) {
                i += 1;
            }

            array[index] = array[i];
            array[i] = temp;
            index = i;

        }
//        for (int k = 0; k < array.length; k++) {
//            System.out.print(array[k] + " ");
//        }
//        System.out.println();
//        System.out.println("i = " + i + " j = " + j + " index = " + index);

        if (first < index - 1)
            quickSort(array, first, index - 1);
        if (last > index + 1)
            quickSort(array, index + 1, last);
    }
}
