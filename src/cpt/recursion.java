package cpt;

import java.util.ArrayList;

public class recursion {
    public static void main(String[] args) {
        System.out.println(numSum(823));
    }

    public static int numSum(int number) {
        if (number / 10 == 0) {
            return number;
        } else {
            return (number % 10) + numSum(number / 10);
        }
    }
}