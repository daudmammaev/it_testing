package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива n: ");
        int n = scanner.nextInt();
        System.out.print("Введите шаг m: ");
        int m = scanner.nextInt();

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

        scanner.close();
    }
}