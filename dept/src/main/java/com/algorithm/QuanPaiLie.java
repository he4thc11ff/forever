package com.algorithm;

import java.util.Scanner;
import java.util.TreeSet;

public class QuanPaiLie {

    static TreeSet<String> treeSet = new TreeSet<>();
    // 字符串全排列
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        char[] chars = str.toCharArray();
        backTrace(chars, 0, str.length());
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (String item: treeSet) {
            sb.append(item).append(", ");
        }
        System.out.println(sb.substring(0, sb.toString().length() - 2) + "]");

    }

    public static void backTrace(char[] chars, int first, int n) {
        if (first == n) {
            treeSet.add(String.valueOf(chars));
//            System.out.println(chars);
        }
        for (int i = first; i < n; i++) {
            swap(chars, first, i);
            backTrace(chars, first + 1, n);
            swap(chars, first, i);

            System.out.print(i + "_");
            System.out.println(chars);

//            1_abcd ok 5
//            3_acbd ok 6
//            2_acbd ok 7
//            3_acdb ok 8
//            3_acbd ok 9
//            2_abcd ok 10
//            3_adcb ok 11
//            2_adcb ok 12
//            3_adbc ok 13
//            3_adcb ok 14
//            3_abcd ok 15

        }
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;

    }

}
