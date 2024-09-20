package com.is;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class Book_rep_json {
    public static String readJsonFromFile(String filePath) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            jsonContent.append(line);
        }

        reader.close();
        return jsonContent.toString();
    }
    public static Book fromJson(String jsonString) {
        
        Map<String, String> map = new HashMap<>();
        jsonString = jsonString.replace("{", "").replace("}", "").replace("\"", "");
        String[] pairs = jsonString.split(",");

        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            map.put(keyValue[0].trim(), keyValue[1].trim());
        }

        String title = map.get("title");
        String author = map.get("author");
        String genere=map.get("genere");
        int quantity = Integer.parseInt(map.get("quantity")); // Преобразование строки в число
        double depositAmount = Double.parseDouble(map.get("depositAmount")); // Преобразование строки в double
        double rentalCost = Double.parseDouble(map.get("rentalCost"));

        return new Book(title, author,genere,quantity,depositAmount,rentalCost);
    } 

}
