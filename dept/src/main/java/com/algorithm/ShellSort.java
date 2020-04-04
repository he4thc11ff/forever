package com.algorithm;

public class ShellSort {
    public static void main(String[] args) {
//        int[] origin = {45, 21, 56, 99, 22, 44, 89, 45, 45, 27, 1, 22, 22, 4, 94, 99, 35, 56, 2};
//        int[] origin = {3,1,5,7,2,4,9,6};
//        int[] origin = {2, 1};
//        insertSort(origin);

        int[] origin = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(origin, origin.length / 2);
        for (int i = 0; i < origin.length; i++) {
            System.out.print(origin[i] + " ");
        }
    }

    public static void shellSort(int[] array, int gap) {
        if (gap == 0) {
            return;
        }

        for (int i = gap; i < array.length; i++) {
            if (array[i] < array[i - gap]) {
                int temp = array[i];
                int j = i - gap;
                while (j >= 0 && temp < array[j]) {
                    array[j + gap] = array[j];
                    j -= gap;
                }
                array[j + gap] = temp;
            }
        }
        shellSort(array, gap / 2);
    }

    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                int temp = array[i];
                int j = i - 1;
                while (j >= 0 && temp < array[j]) {
                    array[j + 1] = array[j];
                    j -= 1;
                }
                array[j + 1] = temp;
            }
        }
    }
}










































