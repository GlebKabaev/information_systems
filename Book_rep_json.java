package com.is;

import java.util.*;
import java.io.*;

public class Book_rep_json implements Book_rep{

    // Чтение JSON из файла
    public static String readFromFile(String filePath) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
        }
        return jsonContent.toString();
    }

    // Преобразование строки JSON в объект Book
    public  Book toBook(String jsonString) {
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
        String genere = map.get("genere");
        int quantity = Integer.parseInt(map.get("quantity"));
        double depositAmount = Double.parseDouble(map.get("depositAmount"));
        double rentalCost = Double.parseDouble(map.get("rentalCost"));

        return new Book(id, title, author, genere, quantity, depositAmount, rentalCost);
    }

    // Запись JSON в файл
    // public static void writeJsonToFile(Book book, String filePath) throws IOException {
    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
    //         writer.write(book.toJson());
    //     }
    // }

    // Запись JSON массива в файл с добавлением новых объектов
    public  void writeToFile(Book book, String filePath) throws IOException {
        File file = new File(filePath);
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        // Если файл существует, читаем его содержимое
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
        }

        // Если файл пуст или не существует, создаём новый JSON массив
        if (lines.isEmpty()) {
            sb.append("[\n").append(book.toJson()).append("\n]");
        } else {
            // Удаляем последний символ "]" для добавления нового объекта
            lines.set(lines.size() - 1, lines.get(lines.size() - 1).replace("]", ""));
            for (String line : lines) {
                sb.append(line).append("\n");
            }
            sb.append(",\n").append(book.toJson()).append("\n]");
        }

        // Записываем обновлённый массив обратно в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(sb.toString());
        }
    }

    // Получение объекта Book по ID из JSON файла
    public  Book getBookById(String filePath, int targetId) throws IOException {
        String jsonString = readFromFile(filePath);
        jsonString = jsonString.trim().replaceAll("^\\[|\\]$", "");  // Убираем скобки

        String[] jsonObjects = jsonString.split("(?<=\\}),");

        // Перебираем объекты и возвращаем нужный по ID
        for (String jsonObject : jsonObjects) {
            jsonObject = jsonObject.trim();
            Map<String, String> map = toMap(jsonObject);

            int id = Integer.parseInt(map.get("id"));
            if (id == targetId) {
                return new Book(id, map.get("title"), map.get("author"), map.get("genere"),
                        Integer.parseInt(map.get("quantity")),
                        Double.parseDouble(map.get("depositAmount")),
                        Double.parseDouble(map.get("rentalCost")));
            }
        }
        return null;  // Возвращаем null, если объект не найден
    }

    // Удаление книги по ID из JSON файла
    public  void deleteBookById(String filePath, int targetId) throws IOException {
        String jsonString = readFromFile(filePath);
        jsonString = jsonString.trim().replaceAll("^\\[|\\]$", "");  // Убираем скобки

        String[] jsonObjects = jsonString.split("(?<=\\}),");
        StringBuilder newJsonContent = new StringBuilder("[\n");

        boolean firstEntry = true;
        boolean found = false;

        // Перебираем объекты и исключаем нужный ID
        for (String jsonObject : jsonObjects) {
            jsonObject = jsonObject.trim();
            Map<String, String> map = toMap(jsonObject);

            int id = Integer.parseInt(map.get("id"));
            if (id != targetId) {
                if (!firstEntry) {
                    newJsonContent.append(",\n");
                }
                newJsonContent.append(formatJsonObject(jsonObject));
                firstEntry = false;
            } else {
                found = true;
            }
        }
        newJsonContent.append("\n]");

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(newJsonContent.toString());
            }
        } else {
            System.out.println("Элемент с id " + targetId + " не найден.");
        }
    }

    // Преобразование строки JSON в map
    private static Map<String, String> toMap(String jsonObject) {
        Map<String, String> map = new HashMap<>();
        String[] pairs = jsonObject.replace("{", "").replace("}", "").replace("\"", "").split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            map.put(keyValue[0].trim(), keyValue[1].trim());
        }
        return map;
    }

    // Форматирование JSON объекта с новыми строками
    private static String formatJsonObject(String jsonObject) {
        String[] pairs = jsonObject.replace("{", "").replace("}", "").split(",");
        StringBuilder formattedJson = new StringBuilder("{\n");
        for (int i = 0; i < pairs.length; i++) {
            formattedJson.append("  ").append(pairs[i].trim());
            if (i < pairs.length - 1) {
                formattedJson.append(",");
            }
            formattedJson.append("\n");
        }
        formattedJson.append("}");
        return formattedJson.toString();
    }
    //замена
    public  void updateBookById(Book newbook,int id,String filepath)throws IOException{
        deleteBookById(filepath,id);
        writeToFile(newbook,filepath);
    }
    // Сортировка книг по полю title
public  void sortBooksByTitle(String filePath) throws IOException {
    String jsonString = readFromFile(filePath);
    jsonString = jsonString.trim().replaceAll("^\\[|\\]$", "");  // Убираем квадратные скобки
    
    String[] jsonObjects = jsonString.split("(?<=\\}),");
    List<Book> bookList = new ArrayList<>();

    // Преобразуем каждый JSON объект в объект Book
    for (String jsonObject : jsonObjects) {
        jsonObject = jsonObject.trim();
        Book book = toBook(jsonObject);
        bookList.add(book);
    }

    // Сортируем список по полю title
    bookList.sort(Comparator.comparing(Book::getTitle));

    // Преобразуем обратно в JSON формат
    StringBuilder sortedJsonContent = new StringBuilder("[\n");
    for (int i = 0; i < bookList.size(); i++) {
        sortedJsonContent.append(bookList.get(i).toJson());
        if (i < bookList.size() - 1) {
            sortedJsonContent.append(",\n");
        }
    }
    sortedJsonContent.append("\n]");

    // Записываем отсортированный JSON обратно в файл
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        writer.write(sortedJsonContent.toString());
    }
}
// Подсчет количества объектов (книг) в JSON файле
public  int getCount(String filePath) throws IOException {
    String jsonString = readFromFile(filePath);
    jsonString = jsonString.trim().replaceAll("^\\[|\\]$", "");  // Убираем квадратные скобки

    // Если файл пуст или содержит только пустые скобки
    if (jsonString.isEmpty()) {
        return 0;
    }

    // Разделяем объекты по шаблону (заканчивающиеся на } с возможным пробелом)
    String[] jsonObjects = jsonString.split("(?<=\\}),");

    // Возвращаем количество объектов
    return jsonObjects.length;
}


}
