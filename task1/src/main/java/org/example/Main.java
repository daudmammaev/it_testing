package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] circularArray = new int[n];
        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }
        StringBuilder path = new StringBuilder();
        int currentIndex = 0;

        do {
            path.append(circularArray[currentIndex]).append(" ");
            currentIndex = (currentIndex + m - 1) % n;
        } while (currentIndex != 0);
        System.out.println("Путь: " + path.toString().trim());
    }
}