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
    public static Book getBookById(String filePath, int targetId) throws IOException {
        // Читаем содержимое файла
        String jsonString = readJsonFromFile(filePath);

        // Убираем начальные и конечные скобки массива
        jsonString = jsonString.trim();
        if (jsonString.startsWith("[")) {
            jsonString = jsonString.substring(1);
        }
        if (jsonString.endsWith("]")) {
            jsonString = jsonString.substring(0, jsonString.length() - 1);
        }

        // Разбиваем строку на отдельные JSON объекты
        String[] jsonObjects = jsonString.split("(?<=\\}),");

        // Перебираем каждый объект
        for (String jsonObject : jsonObjects) {
            jsonObject = jsonObject.trim();

            // Убираем лишние символы
            if (jsonObject.endsWith(",")) {
                jsonObject = jsonObject.substring(0, jsonObject.length() - 1);
            }

            // Преобразуем текущий JSON объект в карту (ключ-значение)
            Map<String, String> map = new HashMap<>();
            String[] pairs = jsonObject.replace("{", "").replace("}", "").replace("\"", "").split(",");

            for (String pair : pairs) {
                String[] keyValue = pair.split(":");
                map.put(keyValue[0].trim(), keyValue[1].trim());
            }

            // Получаем id текущего объекта
            int id = Integer.parseInt(map.get("id"));

            // Если id совпадает с искомым
            if (id == targetId) {
                // Создаем и возвращаем объект Book
                String title = map.get("title");
                String author = map.get("author");
                String genere = map.get("genere");
                int quantity = Integer.parseInt(map.get("quantity"));
                double depositAmount = Double.parseDouble(map.get("depositAmount"));
                double rentalCost = Double.parseDouble(map.get("rentalCost"));

                return new Book(id, title, author, genere, quantity, depositAmount, rentalCost);
            }
        }

        // Если книга с таким id не найдена, возвращаем null
        return null;
    }
    public static void deleteBookById(String filePath, int targetId) throws IOException {
        // Читаем содержимое файла
        String jsonString = readJsonFromFile(filePath);

        // Убираем начальные и конечные скобки массива
        jsonString = jsonString.trim();
        if (jsonString.startsWith("[")) {
            jsonString = jsonString.substring(1);
        }
        if (jsonString.endsWith("]")) {
            jsonString = jsonString.substring(0, jsonString.length() - 1);
        }

        // Разбиваем строку на отдельные JSON объекты
        String[] jsonObjects = jsonString.split("(?<=\\}),");

        // Создаем StringBuilder для сборки нового JSON массива с правильным форматированием
        StringBuilder newJsonContent = new StringBuilder();
        newJsonContent.append("[\n");

        boolean firstEntry = true; // Для правильного добавления запятых
        boolean found = false;     // Флаг, указывающий, найден ли элемент для удаления

        // Перебираем каждый объект
        for (String jsonObject : jsonObjects) {
            jsonObject = jsonObject.trim();

            // Убираем лишние символы
            if (jsonObject.endsWith(",")) {
                jsonObject = jsonObject.substring(0, jsonObject.length() - 1);
            }

            // Преобразуем текущий JSON объект в карту (ключ-значение)
            Map<String, String> map = new HashMap<>();
            String[] pairs = jsonObject.replace("{", "").replace("}", "").replace("\"", "").split(",");

            for (String pair : pairs) {
                String[] keyValue = pair.split(":");
                map.put(keyValue[0].trim(), keyValue[1].trim());
            }

            // Получаем id текущего объекта
            int id = Integer.parseInt(map.get("id"));

            // Если id не совпадает с искомым (оставляем объект), иначе пропускаем (удаляем)
            if (id != targetId) {
                if (!firstEntry) {
                    newJsonContent.append(",\n"); // Добавляем запятую и новую строку между объектами
                }
                newJsonContent.append(formatJsonObject(jsonObject)); // Форматируем каждый объект
                firstEntry = false;
            } else {
                found = true; // Элемент найден и удален
            }
        }

        // Завершаем JSON массив
        newJsonContent.append("\n]");

        // Если элемент был найден и удален, перезаписываем файл с правильным форматированием
        if (found) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
            writer.write(newJsonContent.toString());
            writer.close();
        } else {
            System.out.println("Элемент с id " + targetId + " не найден.");
        }
    }

    // Метод для форматирования JSON объекта с отступами и новыми строками
    private static String formatJsonObject(String jsonObject) {
        jsonObject = jsonObject.trim().replace("{", "").replace("}", "");
        String[] pairs = jsonObject.split(",");

        StringBuilder formattedJson = new StringBuilder();
        formattedJson.append("{\n");

        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i].trim();
            formattedJson.append("  ").append(pair);

            // Если это не последняя пара, добавляем запятую
            if (i < pairs.length - 1) {
                formattedJson.append(",");
            }
            formattedJson.append("\n");
        }

        formattedJson.append("}");
        return formattedJson.toString();
    }
}
