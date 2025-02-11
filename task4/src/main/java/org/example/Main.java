package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("укажите путь к файлу в качестве аргумента командной строки.");
            return;
        }
        String filePath = args[0]; // numbers.txt
        List<Integer> nums = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(" ");
                for (String numStr : numbers) {
                    nums.add(Integer.parseInt(numStr));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }
        int minMoves = calculateMinMoves(nums);
        System.out.println("Минимальное количество ходов: " + minMoves);
    }
    private static int calculateMinMoves(List<Integer> nums) {
        int minMoves = Integer.MAX_VALUE;
        for (int target : nums) {
            int moves = 0;
            for (int num : nums) {
                moves += Math.abs(num - target);
            }
            minMoves = Math.min(minMoves, moves);
        }

        return minMoves;
    }
}