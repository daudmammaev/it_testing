package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("<file1.txt> <file2.txt>");
            return;
        }
        String file1Path = args[0];
        String file2Path = args[1];

        double centerX = 0, centerY = 0, radius = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file1Path))) {
            String[] centerData = br.readLine().split(" ");
            centerX = Double.parseDouble(centerData[0]);
            centerY = Double.parseDouble(centerData[1]);
            radius = Double.parseDouble(br.readLine());
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла 1: " + e.getMessage());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file2Path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] pointData = line.split(" ");
                double pointX = Double.parseDouble(pointData[0]);
                double pointY = Double.parseDouble(pointData[1]);

                double distance = Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));
                // Определяем положение точки
                if (distance < radius) {
                    System.out.println(1); // Точка внутри окружности
                } else if (distance == radius) {
                    System.out.println(0); // Точка на границе окружности
                } else {
                    System.out.println(2); // Точка вне окружности
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла 2: " + e.getMessage());
        }
    }
}