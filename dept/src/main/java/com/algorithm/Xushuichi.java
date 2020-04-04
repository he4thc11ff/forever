package com.algorithm;

import java.util.Random;

public class Xushuichi {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;

//        int[] result = xushuichi(a, 3);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//        }


        for (int i = 0; i < 10000000; i++) {
            int[] result = xushuichi(a, 5);
            for (int j = 0; j < result.length; j++) {
                if (result[j] == 1) {
                    one += 1;
                }
                if (result[j] == 2) {
                    two += 1;
                }
                if (result[j] == 3) {
                    three += 1;
                }
                if (result[j] == 4) {
                    four += 1;
                }
                if (result[j] == 5) {
                    five += 1;
                }
            }
        }

        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
        System.out.println(four);
        System.out.println(five);

    }

    public static int[] xushuichi(int[] origin, int m){
        if (origin.length < m) {
            return origin;
        } else {
            int[] pool = new int[m];
            Random random = new Random();
            for (int i = 0; i < origin.length; i++) {
                if (i < m) {
                    pool[i] = origin[i];
                } else {
                    int index = random.nextInt(i + 1);
                    if (index < m) {
                        pool[index] = origin[i];
                    }
                }
            }
            return pool;
        }
    }

}
