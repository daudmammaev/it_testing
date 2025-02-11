package org.example;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java ReportGenerator <values.json> <tests.json> <report.json>");
            return;
        }

        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];

        try {
            String valuesContent = new String(Files.readAllBytes(Paths.get(valuesFilePath)));
            String testsContent = new String(Files.readAllBytes(Paths.get(testsFilePath)));
            JSONObject valuesJson = new JSONObject(valuesContent);
            JSONObject testsJson = new JSONObject(testsContent);

            Map<String, Object> valuesMap = new HashMap<>();
            for (String key : valuesJson.keySet()) {
                valuesMap.put(key, valuesJson.get(key));
            }

            fillReportStructure(testsJson, valuesMap);
            Files.write(Paths.get(reportFilePath), testsJson.toString(4).getBytes());

            System.out.println("Report generated successfully: " + reportFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillReportStructure(JSONObject testsStructure, Map<String, Object> values) {
        if (testsStructure.has("id")) {
            String testId = testsStructure.getString("id");
            if (values.containsKey(testId)) {
                testsStructure.put("value", values.get(testId));
            } else {
                testsStructure.put("value", (Collection<?>) null);
            }
        }
        for (String key : testsStructure.keySet()) {
            Object value = testsStructure.get(key);
            if (value instanceof JSONObject) {
                fillReportStructure((JSONObject) value, values);
            } else if (value instanceof JSONArray) {
                for (int i = 0; i < ((JSONArray) value).length(); i++) {
                    fillReportStructure(((JSONArray) value).getJSONObject(i), values);
                }
            }
        }
    }
}