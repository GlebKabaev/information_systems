package com.is;

import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        int id = Integer.parseInt(map.get("id"));
        String title = map.get("title");
        String author = map.get("author");
        String genere=map.get("genere");
        int quantity = Integer.parseInt(map.get("quantity")); // Преобразование строки в число
        double depositAmount = Double.parseDouble(map.get("depositAmount")); // Преобразование строки в double
        double rentalCost = Double.parseDouble(map.get("rentalCost"));

        return new Book(id,title, author,genere,quantity,depositAmount,rentalCost);
    } 
    public static void writeJsonToFile(Book book, String filePath) throws IOException {
        String jsonString = book.toJson();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.append(jsonString);
        writer.close();
    }
    public static void writeJsonToFile2(Book book, String filePath) throws IOException {
    File file = new File(filePath);
    StringBuilder sb = new StringBuilder();
    List<String> lines = new ArrayList<>();

    // Если файл существует, читаем его содержимое
    if (file.exists()) {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
    }

    // Если файл пустой или не существует
    if (lines.isEmpty()) {
        sb.append("[\n");  // Создаем начало JSON массива
        sb.append(book.toJson());  // Добавляем первый объект
        sb.append("\n]");
    } else {
        // Если файл содержит данные
        // Удаляем последний символ "]" из файла (конец массива JSON)
        for (int i = 0; i < lines.size() - 1; i++) {
            sb.append(lines.get(i)).append("\n");
        }

        // Добавляем запятую после последнего объекта, чтобы добавить новый объект
        sb.append(",\n").append(book.toJson()).append("\n]");
    }

    // Записываем обратно в файл
    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
    writer.write(sb.toString());
    writer.close();
}
}
